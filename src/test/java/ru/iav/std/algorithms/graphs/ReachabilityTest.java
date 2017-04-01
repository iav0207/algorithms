package ru.iav.std.algorithms.graphs;

import org.apache.commons.lang3.time.StopWatch;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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
        StopWatch timer = new StopWatch();
        timer.start();
        int result = runTest(input);
        timer.stop();
        System.out.printf("Data set size: %d,\tResult: %d,\ttime elapsed: %d ms\n",
                input.adj.length, result, timer.getTime());
    }

    private int runTest(ReachabilityTestData.Input input) {
        return Reachability.reach(input.adj, input.x, input.y);
    }

}