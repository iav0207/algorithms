package ru.iav.std.algorithms.graphs.w3;

import org.testng.annotations.DataProvider;

import java.util.List;

import static ru.iav.std.algorithms.graphs.GraphGenerator.edge;
import static ru.iav.std.algorithms.graphs.GraphGenerator.undirected;

/**
 * Created by takoe on 14.04.17.
 */
public class BFSTestData {

    @DataProvider(name = "samples")
    public static Object[][] samples() {
        return new Object[][] {
                {
                        new Input(
                                undirected(
                                        4,
                                        edge(1, 2),
                                        edge(4, 1),
                                        edge(2, 3),
                                        edge(3, 1)
                                ),
                                1, 3    // s -> t
                        ),
                        2               // expected result
                },
                {
                        new Input(
                                undirected(
                                        5,
                                        edge(5, 2),
                                        edge(1, 3),
                                        edge(3, 4),
                                        edge(1, 4)
                                ),
                                2, 4    // s -> t
                        ),
                        -1              // expected result
                }
        };
    }

    static class Input {
        List<Integer>[] adj;
        int s, t;
        Input(List<Integer>[] adj, int s, int t) {
            this.adj = adj;
            this.s = s;
            this.t = t;
        }
    }

}
