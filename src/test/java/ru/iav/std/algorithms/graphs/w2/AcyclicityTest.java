package ru.iav.std.algorithms.graphs.w2;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.graphs.GraphPerformanceTester;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 01.04.17.
 */
public class AcyclicityTest {

    @Test(dataProviderClass = AcyclicityTestData.class, dataProvider = "sample")
    public void testSample(ArrayList<Integer>[] input, int expected){
        assertEquals(runTest(input), expected);
    }

    @Test(dataProviderClass = AcyclicityTestData.class, dataProvider = "largeSet", invocationCount = 10)
    public void testLargeSet(ArrayList<Integer>[] input, Object ignore) {
        GraphPerformanceTester.resultAndTiming(input, () -> runTest(input));
    }

    private int runTest(ArrayList<Integer>[] input) {
        return Acyclicity.acyclic(input);
    }

}