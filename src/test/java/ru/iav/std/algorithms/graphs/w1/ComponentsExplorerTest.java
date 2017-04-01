package ru.iav.std.algorithms.graphs.w1;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static ru.iav.std.algorithms.util.PerformanceTester.printResultAndExecutionTime;

/**
 * Created by takoe on 01.04.17.
 */
public class ComponentsExplorerTest {

    @Test(dataProvider = "sample", dataProviderClass = ComponentsExplorerTestData.class)
    public void testSample(ArrayList<Integer>[] input, int expected) {
        assertEquals(runTest(input), expected);
    }

    @Test(dataProvider = "largeSet", dataProviderClass = ComponentsExplorerTestData.class,
            invocationCount = 10)
    public void testLargeSet(ArrayList<Integer>[] input, Object ignore) {
        System.out.printf("Data set size: %d,\t", input.length);
        printResultAndExecutionTime(() -> runTest(input));
    }

    private int runTest(ArrayList<Integer>[] input) {
        return ConnectedComponents.numberOfComponents(input);
    }

}