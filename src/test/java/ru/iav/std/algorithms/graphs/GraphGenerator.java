package ru.iav.std.algorithms.graphs;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by takoe on 01.04.17.
 */
public class GraphGenerator {

    private static Random random = ThreadLocalRandom.current();

    private final int n, m;

    public GraphGenerator() {
        this(1000, 1000);
    }

    public GraphGenerator(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public ArrayList<Integer>[] generateAdj() {
        return arrayOfLists(n, generateEdges(n, m));
    }

    public ReachabilityTestData.Input generateReachabilityInput() {
        return new ReachabilityTestData.Input(
                generateAdj(),
                random.nextInt(n) + 1,
                random.nextInt(n) + 1
        );
    }

    @SuppressWarnings("unchecked")
    private static Pair<Integer, Integer>[] generateEdges(int n, int m) {
        return Stream.generate(() -> edge(random.nextInt(n) + 1, random.nextInt(n) + 1)).limit(m)
                .collect(Collectors.toList()).toArray((Pair<Integer, Integer>[]) new Pair[m]);
    }

    static Pair<Integer, Integer> edge(int u, int v) {
        return Pair.of(u, v);
    }

    @SafeVarargs
    static ArrayList<Integer>[] arrayOfLists(int n, Pair<Integer, Integer>... edges) {
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
