package ru.iav.std.algorithms.graphs.w2;

import org.testng.annotations.DataProvider;

import static ru.iav.std.algorithms.graphs.GraphGenerator.directed;
import static ru.iav.std.algorithms.graphs.GraphGenerator.edge;

/**
 * Created by takoe on 01.04.17.
 */
public class AcyclicityTestData {

    @DataProvider(name = "sample")
    public static Object[][] sample() {
        return new Object[][]{
                {
                        directed(4,
                                edge(1, 2),
                                edge(4, 1),
                                edge(2, 3),
                                edge(3, 1)
                        ), 1    // expected result
                },
                {
                        directed(5,
                                edge(1, 2),
                                edge(2, 3),
                                edge(1, 3),
                                edge(3, 4),
                                edge(1, 4),
                                edge(2, 5),
                                edge(3, 5)
                        ), 0    // expected result
                }
        };
    }

}