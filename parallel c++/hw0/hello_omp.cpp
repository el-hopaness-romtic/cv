#include <stdio.h>
#include <omp.h>

int main() {
    // omp_set_num_threads(4);
    
    #pragma omp parallel
    {
        int id = omp_get_thread_num();
        int np = omp_get_num_threads();
        printf("Hello world %d of %d\n", id, np);
    }

    #pragma omp parallel
    {
        int id = omp_get_thread_num();
        int np = omp_get_num_threads();
        int x = 80 / np;
        for (int i = x * id; i < x * (id+1); i++) {
            printf("%d\n", i);
        }
    }

    return 0;
}
