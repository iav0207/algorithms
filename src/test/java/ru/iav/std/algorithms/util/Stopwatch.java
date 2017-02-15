package ru.iav.std.algorithms.util;

/**
 * Created by takoe on 12.02.17.
 */
public class Stopwatch {

    private final long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    public void printElapsedTime() {
        System.out.println("Elapsed time: " + elapsedTime());
    }

}
