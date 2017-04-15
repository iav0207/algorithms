package ru.iav.std.algorithms.graphs.w3;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 15.04.17.
 */
public class BipartiteTest {

    @Test(dataProvider = "samples", dataProviderClass = BipartiteTestData.class)
    public void testSamples(List<Integer>[] adj, boolean expected) {
        assertEquals(new Bipartite(adj).isValidBipartite(), expected);
    }

}