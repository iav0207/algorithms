package ru.iav.std.algorithms.graphs.w3;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 14.04.17.
 */
public class BFSTest {

    @Test(dataProvider = "samples", dataProviderClass = BFSTestData.class)
    public void testSamples(BFSTestData.Input input, int expected) throws Exception {
        BFS bfs = new BFS(input.adj, input.s);
        assertEquals(bfs.distanceTo(input.t), expected);
    }

}