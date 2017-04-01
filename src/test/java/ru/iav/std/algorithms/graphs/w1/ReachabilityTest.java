package ru.iav.std.algorithms.graphs.w1;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static ru.iav.std.algorithms.util.PerformanceTester.printResultAndExecutionTime;

/**
 * Created by takoe on 01.04.17.
 */
public class ReachabilityTest {

    @Test(dataProvider = "samples", dataProviderClass = ReachabilityTestData.class)
    public void testSamples(ReachabilityTestData.Input input, int expected) {
        assertEquals(runTest(input), expected);
    }

    @Test(dataProvider = "largeSet", dataProviderClass = ReachabilityTestData.class, invocationCount = 10)
    public void testLargeSet(ReachabilityTestData.Input input, Object ignore) {
        System.out.printf("Data set size: %d,\t", input.adj.length);
        printResultAndExecutionTime(() -> runTest(input));
    }

    private int runTest(ReachabilityTestData.Input input) {
        return Reachability.reach(input.adj, input.x, input.y);
    }

}