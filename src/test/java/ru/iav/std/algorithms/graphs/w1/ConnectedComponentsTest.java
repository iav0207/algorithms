package ru.iav.std.algorithms.graphs.w1;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.graphs.GraphPerformanceTester;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 01.04.17.
 */
public class ConnectedComponentsTest {

    @Test(dataProvider = "sample", dataProviderClass = ConnectedComponentsTestData.class)
    public void testSample(ArrayList<Integer>[] input, int expected) {
        assertEquals(runTest(input), expected);
    }

    @Test(dataProvider = "largeSet", dataProviderClass = ConnectedComponentsTestData.class, invocationCount = 10)
    public void testLargeSet(ArrayList<Integer>[] input, Object ignore) {
        GraphPerformanceTester.resultAndTiming(input, () -> runTest(input));
    }

    private int runTest(ArrayList<Integer>[] input) {
        return ConnectedComponents.numberOfComponents(input);
    }

}