package ru.iav.std.algorithms.util;

import java.util.function.Supplier;

/**
 * Created by takoe on 18.02.17.
 */
public class PerformanceTester {

    private final double epsilon;

    public PerformanceTester(double epsilon) {
        this.epsilon = epsilon;
    }

    public boolean isEqualExecutionTime(Supplier<?> first, Supplier<?> second) {
        if (epsilon < 0 || epsilon > 1) throw new IllegalArgumentException();
        return measureRelativeDeltaT(first, second) < epsilon;
    }

    public static <T> void printResultAndExecutionTime(Supplier<T> supplier) {
        long start = now();
        T result = supplier.get();
        long time = now() - start;
        System.out.printf("Result: " + result + ",\ttime elapsed: %d ms\n", time);
    }

    public double measureRelativeDeltaT(Supplier<?> first, Supplier<?> second) {
        double timeFirst = measureAverageExecutionTime(first);
        double timeSecond = measureAverageExecutionTime(second);
        return Math.abs(timeFirst - timeSecond) / Math.max(timeFirst, timeSecond);
    }

    public static double measureAverageExecutionTime(Supplier<?> supplier) {
        final int executions = 10;
        long start = now();
        for (int i = 0; i < executions; i++) supplier.get();
        long finish = now();
        return (double) (finish - start) / executions;
    }

    private static long now() {
        return System.currentTimeMillis();
    }

}
