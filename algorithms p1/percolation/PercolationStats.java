/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double Z = 1.96;
    private final double[] fractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0)
            throw new IllegalArgumentException("n should be greater than zero");
        if (trials <= 0)
            throw new IllegalArgumentException("trials should be greater than zero");
        
        fractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            int[] indexes = new int[n * n];
            for (int j = 0; j < n * n; ++j) {
                indexes[j] = j;
            }
            StdRandom.shuffle(indexes);

            Percolation perc = new Percolation(n);
            for (int j = 0; j < indexes.length; j++) {
                int pos = indexes[j];
                int row = pos / n + 1, col = pos % n + 1;
                perc.open(row, col);
                if (perc.percolates())
                    break;
            }
            fractions[i] = (double) perc.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - Z * stddev() / Math.sqrt(fractions.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + Z * stddev() / Math.sqrt(fractions.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]), trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);

        System.out.printf("%-23s = %.16f%n", "mean", ps.mean());
        System.out.printf("%-23s = %.16f%n", "stddev", ps.stddev());
        System.out.printf("%-23s = [%.16f, %.16f]%n", "95% confidence interval", ps.confidenceLo(),
                          ps.confidenceHi());
    }
}
