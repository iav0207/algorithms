package ru.iav.std.algorithms.p1.w1.task;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by takoe on 15.02.17.
 */
public class PercolationStats {

    private final int n;

    private double[] thresholds;

    /**
     * Perform trials independent experiments on an n-by-n grid
     * @param n grid size, must be positive
     * @param trials number of measures, must be non-negative
     */
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 0) throw new IllegalArgumentException();
        this.n = n;
        thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            thresholds[i] = measurePercolationThreshold(new Percolation(n));
        }
    }

    /**
     * Measure relative percolation threshold.
     * @param percolation a percolation object to test.
     * @return A <tt>double</tt> between 0.0 and 1.0.
     */
    private double measurePercolationThreshold(Percolation percolation) {
        int sitesNum = n*n;
        int[] blockedSites = new int[sitesNum];
        for (int i = 0; i < sitesNum; i++) {
            blockedSites[i] = i;
        }
        StdRandom.shuffle(blockedSites);
        int i = 0;
        while (!percolation.percolates()) {
            int site = blockedSites[i];
            percolation.open(row(site), col(site));
            i++;
        }
        return (double) i / (double) sitesNum;
    }

    private int row(int site) {
        return 1 + (site / n);
    }

    private int col(int site) {
        return 1 + (site % n);
    }

    /**
     * Sample mean of percolation threshold
     * @return average percolation threshold
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     *  Sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * Low  endpoint of 95% confidence interval
     * @return
     */
    public double confidenceLo() {
        return mean() - confidenceHalfAnInterval();
    }

    /**
     * high endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHi() {
        return mean() + confidenceHalfAnInterval();
    }

    private double confidenceHalfAnInterval() {
        double studentCoefficient = 1.96;
        return studentCoefficient * stddev() / Math.sqrt(thresholds.length);
    }

    /**
     * Test client
     * @param args (n, T)
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        System.out.printf("\nn = %s, T = %s:\n", args[0], args[1]);

        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("\tmean\t\t\t\t\t= " + stats.mean());
        System.out.println("\tstddev\t\t\t\t\t= " + stats.stddev());
        System.out.printf("\t95%% confidence interval\t= [%f, %f]\n",
                stats.confidenceLo(), stats.confidenceHi());
    }
}
