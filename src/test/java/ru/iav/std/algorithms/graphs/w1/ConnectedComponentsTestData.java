package ru.iav.std.algorithms.graphs.w1;

import org.testng.annotations.DataProvider;
import ru.iav.std.algorithms.graphs.GraphGenerator;

import static ru.iav.std.algorithms.graphs.GraphGenerator.undirected;
import static ru.iav.std.algorithms.graphs.GraphGenerator.edge;

/**
 * Created by takoe on 01.04.17.
 */
public class ConnectedComponentsTestData {

    @DataProvider(name = "sample")
    public static Object[][] sample() {
        return new Object[][]{
                {
                        undirected(4,
                                edge(1, 2),
                                edge(3, 2)
                        ), 2    // expected result
                }
        };
    }

    @DataProvider(name = "largeSet")
    public static Object[][] largeSet() {
        return new Object[][] {{new GraphGenerator(1000, 2000).generateAdj(), null}};
    }

}