#include <iostream>
#include <iomanip>
#include <vector>
#include <cmath>
#define PRINT_ERROR 1

int main(int argc, char *argv[])
{
    using std::cin;
    using std::cout;
    using std::string;
    using std::vector;

    double hx, hy, c1, c2, rtol = 0.001;
    int Nx, Ny, n_iter = 0, max_iter = 1000, check_steps = 10;
    vector<vector<double>> u_next;

    // argument parser
    if (argc > 1)
    {
        string prefix_max_iter = "--max_iter=",
               prefix_check_steps = "--check_steps=",
               prefix_rtol = "--rtol=";
        for (int i = 1; i < argc; ++i)
        {
            string arg = argv[i];
            // max_iter
            if (!arg.compare(0, prefix_max_iter.size(), prefix_max_iter))
                max_iter = std::stoi(arg.substr(prefix_max_iter.size()));

            // check_steps
            else if (!arg.compare(0, prefix_check_steps.size(), prefix_check_steps))
                check_steps = std::stoi(arg.substr(prefix_check_steps.size()));

            // rtol
            else if (!arg.compare(0, prefix_rtol.size(), prefix_rtol))
                rtol = std::stod(arg.substr(prefix_rtol.size()));
        }
    }

    cin >> Nx >> Ny >> hx >> hy;
    u_next = vector<vector<double>>(Nx + 1, vector<double>(Ny + 1, 0));

    for (int i = 0; i < Nx + 1; ++i)
        cin >> u_next[i][0];
    for (int i = 0; i < Nx + 1; ++i)
        cin >> u_next[i][Ny];
    for (int j = 0; j < Ny + 1; ++j)
        cin >> u_next[0][j];
    for (int j = 0; j < Ny + 1; ++j)
        cin >> u_next[Nx][j];

    c1 = hy * hy / (2 * (hx * hx + hy * hy));
    c2 = hx * hx / (2 * (hx * hx + hy * hy));

    for (vector<vector<double>> u_prev = u_next; n_iter < max_iter; n_iter++)
    {
        for (int j = 1; j < Ny; ++j)
            for (int i = 1; i < Nx; ++i)
                u_next[i][j] = c1 * (u_next[i - 1][j] + u_next[i + 1][j]) + c2 * (u_next[i][j - 1] + u_next[i][j + 1]);

        if (n_iter % check_steps == 0)
        {
            bool flag = true;
            for (int i = 1; i < Nx; ++i)
                for (int j = 1; j < Ny; ++j)
                    if (fabs((u_prev[i][j] - u_next[i][j]) / u_next[i][j]) > rtol)
                        flag = false;

            if (flag)
                break;
            else
                u_prev = u_next;
        }
    }

    cout << "n_iter = " << n_iter << '\n';

    cout << std::setprecision(17) << std::fixed;
    for (int i = 0; i < Nx + 1; ++i)
    {
        for (int j = 0; j < Ny; ++j)
            cout << u_next[i][j] << ' ';
        cout << u_next[i][Ny] << '\n';
    }

#if PRINT_ERROR
    auto u = [](double x, double y)
    {
        return x + 2 * x * y + 3 * y;
    };

    cout << "\nAbsolute error:\n";
    for (int i = 0; i < Nx + 1; ++i)
    {
        for (int j = 0; j < Ny; ++j)
            cout << fabs(u_next[i][j] - u(i * hx, j * hy)) << ' ';
        cout << fabs(u_next[i][Ny] - u(i * hx, Ny * hy)) << '\n';
    }
#endif

    return 0;
}
