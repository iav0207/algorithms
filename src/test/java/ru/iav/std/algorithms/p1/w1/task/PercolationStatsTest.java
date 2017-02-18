package ru.iav.std.algorithms.p1.w1.task;

import org.testng.annotations.Test;

/**
 * Created by takoe on 16.02.17.
 */
public class PercolationStatsTest {

    @Test
    public void testMain() throws Exception {
        PercolationStats.main(args(200, 100));
        PercolationStats.main(args(2, 10_000));
        PercolationStats.main(args(2, 100_000));
    }

    private String[] args(int n, int T) {
        return new String[] {String.valueOf(n), String.valueOf(T)};
    }

}