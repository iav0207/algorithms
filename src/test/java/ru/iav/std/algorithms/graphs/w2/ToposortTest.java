package ru.iav.std.algorithms.graphs.w2;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.graphs.GraphPerformanceTester;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 02.04.17.
 */
public class ToposortTest {

    @Test(dataProvider = "sample", dataProviderClass = ToposortTestData.class)
    public void testSample(ArrayList<Integer>[] input, List<Integer> expected) {
        assertEquals(runTest(input), expected);
    }

    @Test(dataProvider = "largeSet", dataProviderClass = ToposortTestData.class, invocationCount = 10)
    public void testLargeSet(ArrayList<Integer>[] input, Object ignore) {
        GraphPerformanceTester.timing(input, () -> runTest(input));
    }

    private List<Integer> runTest(ArrayList<Integer>[] input) {
        return Toposort.toposort(input);
    }

}