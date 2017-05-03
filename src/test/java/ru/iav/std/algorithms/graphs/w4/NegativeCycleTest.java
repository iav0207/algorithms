package ru.iav.std.algorithms.graphs.w4;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.graphs.DirectedWeightedGraph;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 03.05.17.
 */
public class NegativeCycleTest {

    @Test(dataProvider = "samples", dataProviderClass = NegativeCycleTestData.class)
    public void testSamples(DirectedWeightedGraph<Integer> input, boolean expected) {
        NegativeCycle negativeCycle = new NegativeCycle(input.getAdj(), input.getWeights());
        boolean actual = negativeCycle.hasNegativeCycle();
        assertEquals(actual, expected);
    }

}