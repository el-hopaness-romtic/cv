#include <iostream>
#include <iomanip>
#include <cstring>
#include <chrono>
#include <mpi.h>

#define PRINT_ERROR 1
#define COMPUTATION_TAG 123
#define SYNCED_OUTPUT_TAG 234
#define PRINT_ERROR_TAG 345

using std::cin;
using std::cout;

template <typename T>
T parse_args(int argc, char *argv[], std::string prefix, T default_value, auto converter)
{
    T value = default_value;
    for (int i = 1; i < argc; ++i)
    {
        std::string arg = argv[i];
        if (!arg.compare(0, prefix.size(), prefix))
            value = converter(arg.substr(prefix.size()));
    }
    return value;
}

std::tuple<int, int, double, double> read_values(std::istream &stream)
{
    int Nx, Ny;
    double hx, hy;
    stream >> Nx >> Ny >> hx >> hy;
    return {Nx + 1, Ny + 1, hx, hy};
}

double fabs(double value)
{
    return value < 0 ? -value : value;
}

void synced_input(int rank, int size, double **a, int Nx_total, int start, int end, int Ny)
{
    double ignore;

    for (int i = 0; i < Nx_total; ++i)
        if (start <= i && i < end)
            cin >> a[i - start][0];
        else
            cin >> ignore;

    for (int i = 0; i < Nx_total; ++i)
        if (start <= i && i < end)
            cin >> a[i - start][Ny - 1];
        else
            cin >> ignore;

    for (int j = 0; j < Ny; ++j)
        if (rank == 0)
            cin >> a[0][j];
        else
            cin >> ignore;

    for (int j = 0; j < Ny; ++j)
        if (rank == size - 1)
            cin >> a[end - start - 1][j];
        else
            cin >> ignore;
}

void synced_output(int rank, int size, double **a, int Nx_total, int Nx, int Ny)
{
    if (rank != 0)
    {
        for (int i = 0; i < Nx; ++i)
        {
            MPI_Ssend(a[i], Ny, MPI_DOUBLE, 0, SYNCED_OUTPUT_TAG, MPI_COMM_WORLD);
        }
    }
    else
    {
        double temp[Ny];

        for (int i = 0; i < Nx; ++i)
        {
            for (int j = 0; j < Ny - 1; ++j)
                cout << a[i][j] << ' ';
            cout << a[i][Ny - 1] << '\n';
        }

        for (int rank_src = 1; rank_src < size; ++rank_src)
        {
            const int start_src = rank_src * Nx_total / size,
                      end_src = (rank_src + 1) * Nx_total / size;

            for (int i = start_src; i < end_src; ++i)
            {
                MPI_Recv(temp, Ny, MPI_DOUBLE, rank_src, SYNCED_OUTPUT_TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
                for (int j = 0; j < Ny - 1; ++j)
                    cout << temp[j] << ' ';
                cout << temp[Ny - 1] << '\n';
            }
        }
    }
}

int main(int argc, char *argv[])
{
    std::chrono::steady_clock::time_point time_start = std::chrono::steady_clock::now();

    int rank, size;
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    const std::string prefix_rtol = "--rtol=",
                      prefix_max_iter = "--max-iter=",
                      prefix_check_steps = "--check-steps=";

    const double rtol = parse_args(argc, argv, prefix_rtol, 0.001, [](std::string x){ return std::stod(x); });
    const int max_iter = parse_args(argc, argv, prefix_max_iter, 20000, [](std::string x){ return std::stoi(x); }),
              check_steps = parse_args(argc, argv, prefix_check_steps, 10, [](std::string x){ return std::stoi(x); });

    auto const [Nx_total, Ny_total, hx, hy] = read_values(cin);
    const int start = rank * Nx_total / size,
              end = (rank + 1) * Nx_total / size,
              Nx = end - start,
              Ny = Ny_total;
    const double c1 = hy * hy / (2 * (hx * hx + hy * hy)),
                 c2 = hx * hx / (2 * (hx * hx + hy * hy));
    const auto deep_copy = [Nx, Ny](double **a, double **b)
    {
        for (int i = 0; i < Nx; ++i)
            for (int j = 0; j < Ny; ++j)
                a[i][j] = b[i][j];
    };

    double *u_prev[Nx], *u_next[Nx];
    for (int i = 0; i < Nx; ++i)
    {
        u_prev[i] = new double[Ny]{0};
        u_next[i] = new double[Ny]{0};
    }
    synced_input(rank, size, u_next, Nx_total, start, end, Ny);
    deep_copy(u_prev, u_next);

    const bool has_block_above = rank != 0,
               has_block_below = rank != size - 1;
    if (has_block_above)
        MPI_Ssend(u_next[0] + 1, Ny - 2, MPI_DOUBLE, rank - 1, COMPUTATION_TAG, MPI_COMM_WORLD);
    double recv_cache[Ny - 1], *buf = recv_cache + 1;

    MPI_Request req1 = MPI_REQUEST_NULL,
                req2 = MPI_REQUEST_NULL;
    bool flag = true;
    int n_iter = check_steps;
    while (n_iter < max_iter)
    {
        for (int k = 0; k < check_steps; ++k)
        {
            if (has_block_above)
            {
                MPI_Recv(buf, Ny - 2, MPI_DOUBLE, rank - 1, COMPUTATION_TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

                MPI_Wait(&req1, MPI_STATUS_IGNORE);
                for (int j = 1; j < Ny - 1; ++j)
                    u_next[0][j] = c1 * (recv_cache[j] + u_next[1][j]) + c2 * (u_next[0][j - 1] + u_next[0][j + 1]);

                MPI_Isend(u_next[0] + 1, Ny - 2, MPI_DOUBLE, rank - 1, COMPUTATION_TAG, MPI_COMM_WORLD, &req1);
            }

            for (int i = 1; i < Nx - 1; ++i)
                for (int j = 1; j < Ny - 1; ++j)
                    u_next[i][j] = c1 * (u_next[i - 1][j] + u_next[i + 1][j]) + c2 * (u_next[i][j - 1] + u_next[i][j + 1]);

            if (has_block_below)
            {
                MPI_Recv(buf, Ny - 2, MPI_DOUBLE, rank + 1, COMPUTATION_TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

                MPI_Wait(&req2, MPI_STATUS_IGNORE);
                for (int j = 1; j < Ny - 1; ++j)
                    u_next[Nx - 1][j] = c1 * (u_next[Nx - 2][j] + recv_cache[j]) + c2 * (u_next[Nx - 1][j - 1] + u_next[Nx - 1][j + 1]);

                MPI_Isend(u_next[Nx - 1] + 1, Ny - 2, MPI_DOUBLE, rank + 1, COMPUTATION_TAG, MPI_COMM_WORLD, &req2);
            }
        }

        for (int i = 0; i < Nx && flag; ++i)
            for (int j = 1; j < Ny - 1; ++j)
                if (fabs((u_prev[i][j] - u_next[i][j]) / u_next[i][j]) > rtol)
                {
                    flag = false;
                    break;
                }

        MPI_Allreduce(&flag, &flag, 1, MPI_CXX_BOOL, MPI_LAND, MPI_COMM_WORLD);
        if (flag || n_iter >= max_iter)
            break;

        deep_copy(u_prev, u_next);
        n_iter += check_steps;
        flag = true;
    }

    synced_output(rank, size, u_next, Nx_total, Nx, Ny);
    std::chrono::steady_clock::time_point time_end = std::chrono::steady_clock::now();
    if (rank == 0)
    {
        cout << std::setprecision(15) << std::fixed
             << size << " processes\n"
             << "Calculation time = " << std::chrono::duration_cast<std::chrono::seconds>(time_end - time_start).count() << " s\n"
             << "n_iter = " << n_iter << '\n';
    }

#if PRINT_ERROR
    const auto u = [](double x, double y)
    { return x + 2 * x * y + 3 * y; };

    for (int i = 0; i < Nx; ++i)
        for (int j = 0; j < Ny; ++j)
            u_next[i][j] = fabs(u_next[i][j] - u((start + i) * hx, j * hy));

    if (rank == 0)
        cout << "\nAbsolute error:\n";
    synced_output(rank, size, u_next, Nx_total, Nx, Ny);
#endif

    MPI_Finalize();
    for (int i = 0; i < Nx; ++i)
    {
        delete u_next[i];
        delete u_prev[i];
    }
    return 0;
}
