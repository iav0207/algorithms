package ru.iav.std.algorithms.graphs.w2;

import org.testng.annotations.DataProvider;
import ru.iav.std.algorithms.graphs.GraphGenerator;

import java.util.Arrays;

import static ru.iav.std.algorithms.graphs.GraphGenerator.directed;
import static ru.iav.std.algorithms.graphs.GraphGenerator.edge;

/**
 * Created by takoe on 02.04.17.
 */
public class ToposortTestData {

    @DataProvider(name = "sample")
    public static Object[][] sample() {
        return new Object[][] {
                {
                        directed(4,
                                edge(1, 2),
                                edge(4, 1),
                                edge(3, 1)
                        ),
                        Arrays.asList(3, 2, 0, 1)       // expected result
                },
                {
                        directed(4,
                                edge(3, 1)
                        ),
                        Arrays.asList(3, 2, 1, 0)       // expected result
                },
                {
                        directed(5,
                                edge(2, 1),
                                edge(3, 2),
                                edge(3, 1),
                                edge(4, 3),
                                edge(4, 1),
                                edge(5, 2),
                                edge(5, 3)
                        ),
                        Arrays.asList(4, 3, 2, 1, 0)    // expected result
                }
        };
    }

    @DataProvider(name = "largeSet")
    public static Object[][] largeSet() {
        return new Object[][] {{new GraphGenerator(100000, 100000).generateDirected(), null}};
    }

}
