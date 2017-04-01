package ru.iav.std.algorithms.graphs;

import org.apache.commons.lang3.tuple.Pair;
import ru.iav.std.algorithms.graphs.w1.Reachability;
import ru.iav.std.algorithms.graphs.w1.ReachabilityTestData;

import java.util.ArrayList;
import java.util.Arrays;
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

    public ArrayList<Integer>[] generateUndirected() {
        return undirected(n, generateEdges(n, m));
    }

    public ArrayList<Integer>[] generateDirected() {
        return directed(n, generateEdges(n, m));
    }

    public ReachabilityTestData.Input generateReachabilityInput() {
        return new ReachabilityTestData.Input(
                generateUndirected(),
                random.nextInt(n) + 1,
                random.nextInt(n) + 1
        );
    }

    @SuppressWarnings("unchecked")
    private static Pair<Integer, Integer>[] generateEdges(int n, int m) {
        return Stream.generate(() -> edge(random.nextInt(n) + 1, random.nextInt(n) + 1)).limit(m)
                .collect(Collectors.toList()).toArray((Pair<Integer, Integer>[]) new Pair[m]);
    }

    public static Pair<Integer, Integer> edge(int u, int v) {
        return Pair.of(u, v);
    }

    @SafeVarargs
    public static ArrayList<Integer>[] undirected(int n, Pair<Integer, Integer>... edges) {
        ArrayList<Integer>[] array = Reachability.initializeAdjArray(n);
        Arrays.stream(edges).forEach(e -> putUndirectedEdge(array, e));
        return array;
    }

    @SafeVarargs
    public static ArrayList<Integer>[] directed(int n, Pair<Integer, Integer>... edges) {
        ArrayList<Integer>[] array = Reachability.initializeAdjArray(n);
        Arrays.stream(edges).forEach(e -> putDirectedEdge(array, e));
        return array;
    }

    private static void putDirectedEdge(ArrayList<Integer>[] adj, Pair<Integer, Integer> edge) {
        int u = edge.getLeft() - 1;
        int v = edge.getRight() - 1;
        adj[u].add(v);
    }

    private static void putUndirectedEdge(ArrayList<Integer>[] adj, Pair<Integer, Integer> edge) {
        int u = edge.getLeft() - 1;
        int v = edge.getRight() - 1;
        adj[u].add(v);
        adj[v].add(u);
    }

}
