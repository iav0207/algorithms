package ru.iav.std.algorithms.p1.w1.task;

import edu.princeton.cs.algs4.Stopwatch;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static edu.princeton.cs.algs4.StdRandom.uniform;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by takoe on 15.02.17.
 */
public class PercolationTest {

    private Stopwatch stopwatch = new Stopwatch();

    @Test(expectedExceptions = IllegalArgumentException.class,
            dataProvider = "negativeIntegers")
    public void shouldConstructorThrowExceptionIfNIsNotPositive(int n) throws Exception {
        new Percolation(n);
    }

    @DataProvider(name = "negativeIntegers")
    public static Object[][] negativeIntegers() {
        int n = 10;
        Object[][] data = new Object[n][1];
        data[0][0] = 0;
        for (int i = 1; i < n; i++) {
            data[i][0] = -uniform(100);
        }
        return data;
    }

    @Test
    public void shouldNotPercolateJustAfterInitialization() throws Exception {
        assertFalse(new Percolation(100).percolates());
    }

    @Test
    public void shouldConstructorTakeLessThanNSquaredTime() throws Exception {
        int measuresCount = 10;
        double[] t = new double[measuresCount];
        for (int i = 0; i < measuresCount; i++) {
            int n = 10 * (int) Math.pow(2, i);
            t[i] = measureConstructorInitializationTime(n);
            System.out.printf("%d.\tN = %d:\tt = %f\n", i + 1, n, t[i]);
        }
        double orderOfGrowth = calculateAverageLog(t);
        System.out.println("Average log(T(N)/T(N/2)) = " + orderOfGrowth);

        assertTrue(orderOfGrowth < 2.0);
    }

    private double measureConstructorInitializationTime(int n) {
        double start = now();
        new Percolation(n);
        return now() - start;
    }

    private double calculateAverageLog(double[] t) {
        double[] ratios = new double[t.length - 1];
        for (int i = 0; i < t.length - 1; i++) {
            ratios[i] = t[i+1] / t[i];
        }
        System.out.println();
        double[] logRatios = Arrays.stream(ratios)
                .filter(Double::isFinite)
                .filter(r -> r > 0)
                .map(this::logBaseTwo)
                .toArray();
        Arrays.stream(logRatios).forEach(System.out::println);
        return Arrays.stream(logRatios).average().orElseThrow(RuntimeException::new);
    }

    private double logBaseTwo(double value) {
        return Math.log(value) / Math.log(2);
    }

    private double now() {
        return 1_000_000 * stopwatch.elapsedTime();
    }

}