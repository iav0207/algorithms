package ru.iav.std.algorithms.graphs.w4;

import org.testng.annotations.DataProvider;
import ru.iav.std.algorithms.graphs.DirectedWeightedGraph;

import static ru.iav.std.algorithms.graphs.GraphGenerator.weightedGraphBuilder;

/**
 * Created by takoe on 15.04.17.
 */
public class DijkstraTestData {

    @DataProvider(name = "samples")
    @SuppressWarnings("unchecked")
    public static Object[][] samples() {
        return new Object[][] {
                {
                        new Input(
                                weightedGraphBuilder()
                                        .addEdge(1, 2, 1)
                                        .addEdge(4, 1, 2)
                                        .addEdge(2, 3, 2)
                                        .addEdge(1, 3, 5)
                                        .build(),
                                0, 2
                        ),
                        3
                },
                {
                        new Input(
                                weightedGraphBuilder()
                                        .addEdge(1, 2, 4)
                                        .addEdge(1, 3, 2)
                                        .addEdge(2, 3, 2)
                                        .addEdge(3, 2, 1)
                                        .addEdge(2, 4, 2)
                                        .addEdge(3, 5, 4)
                                        .addEdge(5, 4, 1)
                                        .addEdge(2, 5, 3)
                                        .addEdge(3, 4, 4)
                                        .build(),
                                0, 4
                        ),
                        6
                },
                {
                        new Input(
                                weightedGraphBuilder()
                                        .addEdge(1, 2, 7)
                                        .addEdge(1, 3, 5)
                                        .addEdge(2, 3, 2)
                                        .build(),
                                2, 1
                        ),
                        -1
                }
        };
    }

    static class Input {
        DirectedWeightedGraph<Integer> graph;
        int s, t;
        Input(DirectedWeightedGraph<Integer> graph, int s, int t) {
            this.graph = graph;
            this.s = s;
            this.t = t;
        }
    }

}
