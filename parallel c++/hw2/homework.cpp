#include <iostream>
#include <iomanip>
#include <omp.h>
#include <cstring>
#include <chrono>
#include <atomic>
#include "semaphore.cpp"
#define PRINT_ERROR 1

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
    return {Nx, Ny, hx, hy};
}

double fabs(double value)
{
    return value > 0 ? value : -value;
}

int main(int argc, char *argv[])
{
    std::chrono::steady_clock::time_point begin = std::chrono::steady_clock::now();
    using std::cin;
    using std::cout;

    const std::string prefix_rtol = "--rtol=",
                      prefix_max_iter = "--max-iter=",
                      prefix_check_steps = "--check-steps=",
                      prefix_number_of_threads = "--number-of-threads=";

    const double rtol = parse_args(argc, argv, prefix_rtol, 0.001, [](std::string x)
                                   { return std::stod(x); });
    const int max_iter = parse_args(argc, argv, prefix_max_iter, 1000000, [](std::string x)
                                    { return std::stoi(x); }),
              check_steps = parse_args(argc, argv, prefix_check_steps, 10, [](std::string x)
                                       { return std::stoi(x); }),
              number_of_threads = parse_args(argc, argv, prefix_number_of_threads, 2, [](std::string x)
                                             { return std::stoi(x); });

    std::atomic_bool flag(true);
    int n_iter = check_steps;
    auto const [Nx, Ny, hx, hy] = read_values(cin);
    const auto deep_copy = [Nx, Ny](double **a, double **b)
    {
        for (int i = 0; i < Nx + 1; ++i)
            for (int j = 0; j < Ny + 1; ++j)
                a[i][j] = b[i][j];
    };
    const double c1 = hy * hy / (2 * (hx * hx + hy * hy)),
                 c2 = hx * hx / (2 * (hx * hx + hy * hy));

    omp_set_num_threads(number_of_threads);
    double *u_prev[Nx + 1], *u_next[Nx + 1];
    for (int i = 0; i < Nx + 1; ++i)
    {
        u_prev[i] = new double[Ny + 1]{0};
        u_next[i] = new double[Ny + 1]{0};
    }

    for (int i = 0; i < Nx + 1; ++i)
        cin >> u_next[i][0];
    for (int i = 0; i < Nx + 1; ++i)
        cin >> u_next[i][Ny];
    for (int j = 0; j < Ny + 1; ++j)
        cin >> u_next[0][j];
    for (int j = 0; j < Ny + 1; ++j)
        cin >> u_next[Nx][j];
    deep_copy(u_prev, u_next);

    int upper_bounds[number_of_threads]{[0] = 1},
        lower_bounds[number_of_threads];

#pragma omp parallel for schedule(static)
    for (int i = 1; i < Nx; ++i)
        lower_bounds[omp_get_thread_num()] = i;

    for (int i = 1; i < number_of_threads; ++i)
        upper_bounds[i] = lower_bounds[i - 1] + 1;

    semaphore semaphore_downward[number_of_threads - 1],
        semaphore_upward[number_of_threads - 1];

    for (int i = 0; i < number_of_threads - 1; ++i)
        semaphore_downward[i].release();

    double cache_downward[number_of_threads - 1][Ny],
        cache_upward[number_of_threads - 1][Ny];

    for (int i = 0; i < number_of_threads - 1; i++)
    {
        for (int j = 0; j < Ny; j++)
        {
            cache_downward[i][j] = 0;
            cache_upward[i][j] = 0;
        }
    }

#pragma omp parallel
    {
        const int thread_num = omp_get_thread_num(),
                  upper_bound = upper_bounds[thread_num],
                  lower_bound = lower_bounds[thread_num];
        const bool upper_condition = upper_bound != 1,
                   lower_condition = lower_bound != Nx - 1;
        while (n_iter < max_iter)
        {
            for (int k = 0; k < check_steps; ++k)
            {
                int i = upper_bound;
                if (upper_condition)
                {
                    semaphore_upward[thread_num - 1].acquire();
                    for (int j = 1; j < Ny; ++j)
                    {
                        u_next[i][j] = c1 * (cache_downward[thread_num - 1][j] + u_next[i + 1][j]) + c2 * (u_next[i][j - 1] + u_next[i][j + 1]);
                        cache_upward[thread_num - 1][j] = u_next[i][j];
                    }
                    semaphore_downward[thread_num - 1].release();
                }
                else
                {
                    for (int j = 1; j < Ny; ++j)
                        u_next[i][j] = c1 * (u_next[i - 1][j] + u_next[i + 1][j]) + c2 * (u_next[i][j - 1] + u_next[i][j + 1]);
                }

                for (i = upper_bound + 1; i < lower_bound; ++i)
                    for (int j = 1; j < Ny; ++j)
                        u_next[i][j] = c1 * (u_next[i - 1][j] + u_next[i + 1][j]) + c2 * (u_next[i][j - 1] + u_next[i][j + 1]);

                if (lower_condition)
                {
                    semaphore_downward[thread_num].acquire();
                    for (int j = 1; j < Ny; ++j)
                    {
                        u_next[i][j] = c1 * (u_next[i - 1][j] + cache_upward[thread_num][j]) + c2 * (u_next[i][j - 1] + u_next[i][j + 1]);
                        cache_downward[thread_num][j] = u_next[i][j];
                    }
                    semaphore_upward[thread_num].release();
                }
                else
                {
                    for (int j = 1; j < Ny; ++j)
                        u_next[i][j] = c1 * (u_next[i - 1][j] + u_next[i + 1][j]) + c2 * (u_next[i][j - 1] + u_next[i][j + 1]);
                }
            }

#pragma omp for
            for (int i = 1; (i < Nx); ++i)
            {
                if (flag)
                    for (int j = 1; j < Ny; ++j)
                        if (fabs((u_prev[i][j] - u_next[i][j]) / u_next[i][j]) > rtol)
                        {
                            flag = false;
                            break;
                        }
            }

            if (flag || n_iter >= max_iter)
                break;

#pragma omp barrier
#pragma omp single
            {
                deep_copy(u_prev, u_next);
                n_iter += check_steps;
                flag = true;
            }
        }
    }

    std::chrono::steady_clock::time_point end = std::chrono::steady_clock::now();
    cout << number_of_threads << " threads\n"
         << "Calculation time = " << std::chrono::duration_cast<std::chrono::seconds>(end - begin).count() << " s\n"
         << "n_iter = " << n_iter << '\n';
    cout << std::setprecision(15) << std::fixed;
    for (int i = 0; i < Nx + 1; ++i)
    {
        for (int j = 0; j < Ny; ++j)
            cout << u_next[i][j] << ' ';
        cout << u_next[i][Ny] << '\n';
    }

#if PRINT_ERROR
    auto u = [](double x, double y)
    { return x + 2 * x * y + 3 * y; };

    cout << "\nAbsolute error:\n";
    for (int i = 0; i < Nx + 1; ++i)
    {
        for (int j = 0; j < Ny; ++j)
            cout << fabs(u_next[i][j] - u(i * hx, j * hy)) << ' ';
        cout << fabs(u_next[i][Ny] - u(i * hx, Ny * hy)) << '\n';
    }
#endif
    for (int i = 0; i < Nx + 1; ++i)
    {
        delete u_next[i];
        delete u_prev[i];
    }
    return 0;
}
