package ru.iav.std.algorithms.graphs;

import org.testng.annotations.DataProvider;

import static ru.iav.std.algorithms.graphs.GraphGenerator.arrayOfLists;
import static ru.iav.std.algorithms.graphs.GraphGenerator.edge;

/**
 * Created by takoe on 01.04.17.
 */
public class ComponentsExplorerTestData {

    @DataProvider(name = "sample")
    public static Object[][] sample() {
        return new Object[][]{
                {
                        arrayOfLists(4,
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