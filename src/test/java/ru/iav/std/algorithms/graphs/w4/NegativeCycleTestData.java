package ru.iav.std.algorithms.graphs.w4;

import org.testng.annotations.DataProvider;

import static ru.iav.std.algorithms.graphs.GraphGenerator.weightedGraphBuilder;

/**
 * Created by takoe on 03.05.17.
 */
@SuppressWarnings("unchecked")
public class NegativeCycleTestData {

    @DataProvider(name = "samples")
    public static Object[][] samples() {
        return new Object[][]{
                {
                        weightedGraphBuilder()
                                .addEdge(1, 2, -5)
                                .addEdge(4, 1, 2)
                                .addEdge(2, 3, 2)
                                .addEdge(3, 1, 1)
                                .build(),
                        true
                },
                {
                        weightedGraphBuilder()
                                .addEdge(1, 2, 0)
                                .addEdge(4, 1, 2)
                                .addEdge(2, 3, 2)
                                .addEdge(3, 1, 1)
                                .build(),
                        false
                }
        };
    }

}