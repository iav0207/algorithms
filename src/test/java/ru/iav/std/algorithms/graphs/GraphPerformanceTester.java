package ru.iav.std.algorithms.graphs;

import java.util.ArrayList;
import java.util.function.Supplier;

import static ru.iav.std.algorithms.util.PerformanceTester.printExecutionTime;
import static ru.iav.std.algorithms.util.PerformanceTester.printResultAndExecutionTime;

/**
 * Created by takoe on 02.04.17.
 */
public class GraphPerformanceTester {

    public static <T> void resultAndTiming(ArrayList<Integer>[] input, Supplier<T> testRunner) {
        printDataSetSize(input);
        printResultAndExecutionTime(testRunner);
    }

    public static <T> void timing(ArrayList<Integer>[] input, Supplier<T> testRunner) {
        printDataSetSize(input);
        printExecutionTime(testRunner);
    }

    private static void printDataSetSize(ArrayList<Integer>[] input) {
        System.out.printf("Data set size: %d,\t", input.length);
    }

}
