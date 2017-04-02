package ru.iav.std.algorithms.graphs.w2;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.graphs.GraphPerformanceTester;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static ru.iav.std.algorithms.graphs.w2.StronglyConnected.buildReverseGraph;

/**
 * Created by takoe on 02.04.17.
 */
public class StronglyConnectedTest {

    @Test(dataProviderClass = StronglyConnectedTestData.class, dataProvider = "sample")
    public void testSample(ArrayList<Integer>[] input, int expected) {
        assertEquals(runTest(input), expected);
    }

    @Test(dataProviderClass = StronglyConnectedTestData.class, dataProvider = "largeSet", invocationCount = 10)
    public void testLargeSet(ArrayList<Integer>[] input, Object ignore) {
        ArrayList<Integer>[] reverse = buildReverseGraph(input);
        GraphPerformanceTester.resultAndTiming(input, () -> runTest(input, reverse));
    }

    private int runTest(ArrayList<Integer>[] input) {
        return StronglyConnected.numberOfStronglyConnectedComponents(input, buildReverseGraph(input));
    }

    private int runTest(ArrayList<Integer>[] input, ArrayList<Integer>[] reverse) {
        return StronglyConnected.numberOfStronglyConnectedComponents(input, reverse);
    }

}