#include <iostream>
#include <iomanip>

int main()
{
    using std::cout;

    double x1 = 3,
           y1 = 1;
    int Nx = 700,
        Ny = 250;
    double hx = x1 / Nx,
           hy = y1 / Ny;

    auto u = [](double x, double y)
    { return x + 2 * x * y + 3 * y; };

    cout << std::setprecision(17) << std::fixed;
    cout << Nx << ' ' << Ny << '\n';
    cout << hx << ' ' << hy << '\n';

    // u(x, y)|_{y=0}
    for (int i = 0; i < Nx + 1; ++i)
        cout << u(i * hx, 0 * hy) << ' ';
    cout << '\n';

    // u(x, y)|_{y=y1}
    for (int i = 0; i < Nx + 1; ++i)
        cout << u(i * hx, Ny * hy) << ' ';
    cout << '\n';

    // u(x, y)|_{x=0}
    for (int j = 0; j < Ny + 1; ++j)
        cout << u(0 * hx, j * hy) << ' ';
    cout << '\n';

    // u(x, y)|_{x=x1}
    for (int j = 0; j < Ny + 1; ++j)
        cout << u(Nx * hx, j * hy) << ' ';
    cout << '\n';

    return 0;
}