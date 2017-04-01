package ru.iav.std.algorithms.graphs;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by takoe on 01.04.17.
 */
public class ReachabilityTestData {

    private static Random random = ThreadLocalRandom.current();

    public static class Input {
        ArrayList<Integer>[] adj;
        int x, y;
        Input(ArrayList<Integer>[] adj, int x, int y) {
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
        final int n = 1000, m = 1000;
        return new Object[][] {
                {
                    new Input(
                            arrayOfLists(n, generateEdges(n, m)),
                            random.nextInt(n) + 1,
                            random.nextInt(n) + 1
                    ), null
                }
        };
    }

    @SuppressWarnings("unchecked")
    private static Pair<Integer, Integer>[] generateEdges(int n, int m) {
        return Stream.generate(() -> edge(random.nextInt(n) + 1, random.nextInt(n) + 1)).limit(m)
                .collect(Collectors.toList()).toArray((Pair<Integer, Integer>[]) new Pair[m]);
    }

    private static Pair<Integer, Integer> edge(int u, int v) {
        return Pair.of(u, v);
    }

    @SafeVarargs
    private static ArrayList<Integer>[] arrayOfLists(int n, Pair<Integer, Integer>... edges) {
        ArrayList<Integer>[] array = Reachability.initializeAdjArray(n);
        for (Pair<Integer, Integer> eachEdge : edges) {
            int u = eachEdge.getLeft() - 1;
            int v = eachEdge.getRight() - 1;
            array[u].add(v);
            array[v].add(u);
        }
        return array;
    }

}
