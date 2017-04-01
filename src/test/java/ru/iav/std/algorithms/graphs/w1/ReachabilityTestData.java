package ru.iav.std.algorithms.graphs.w1;

import org.testng.annotations.DataProvider;
import ru.iav.std.algorithms.graphs.GraphGenerator;

import java.util.ArrayList;

import static ru.iav.std.algorithms.graphs.GraphGenerator.arrayOfLists;
import static ru.iav.std.algorithms.graphs.GraphGenerator.edge;

/**
 * Created by takoe on 01.04.17.
 */
public class ReachabilityTestData {

    public static class Input {

        ArrayList<Integer>[] adj;

        int x, y;

        public Input(ArrayList<Integer>[] adj, int x, int y) {
            this.adj = adj;
            this.x = x - 1;
            this.y = y - 1;
        }
    }

    @DataProvider(name = "samples")
    public static Object[][] samples() {
        return new Object[][] {
                {
                        new Input(
                                arrayOfLists(4,
                                        edge(1, 2),
                                        edge(3, 2),
                                        edge(4, 3),
                                        edge(1, 4)
                                ),
                                1, 4
                        ), 1    // expected result
                },
                {
                        new Input(
                                arrayOfLists(4,
                                        edge(1, 2),
                                        edge(3, 2)
                                ),
                                1, 4
                        ), 0    // expected result
                }
        };
    }

    @DataProvider(name = "largeSet")
    public static Object[][] largeSet() {
        return new Object[][] {{new GraphGenerator().generateReachabilityInput(), null}};
    }

}
