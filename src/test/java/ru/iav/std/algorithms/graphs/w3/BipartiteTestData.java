package ru.iav.std.algorithms.graphs.w3;

import org.testng.annotations.DataProvider;

import static ru.iav.std.algorithms.graphs.GraphGenerator.edge;
import static ru.iav.std.algorithms.graphs.GraphGenerator.undirected;

/**
 * Created by takoe on 15.04.17.
 */
public class BipartiteTestData {

    @DataProvider(name = "samples")
    public static Object[][] samples() {
        return new Object[][]{
                {
                        undirected(
                                4,
                                edge(1, 2),
                                edge(4, 1),
                                edge(2, 3),
                                edge(3, 1)
                        ),
                        false
                },
                {
                        undirected(
                                5,
                                edge(5, 2),
                                edge(4, 2),
                                edge(3, 4),
                                edge(1, 4)
                        ),
                        true
                }
        };
    }

}
