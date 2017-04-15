package ru.iav.std.algorithms.graphs.w4;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 15.04.17.
 */
public class DijkstraTest {

    @Test(dataProvider = "samples", dataProviderClass = DijkstraTestData.class)
    public void testSamples(DijkstraTestData.Input input, int expected) {
        Dijkstra dijkstra = new Dijkstra(
                input.graph.getAdj(),
                input.graph.getWeights(),
                input.s);
        assertEquals(dijkstra.distanceTo(input.t), expected);
    }

}