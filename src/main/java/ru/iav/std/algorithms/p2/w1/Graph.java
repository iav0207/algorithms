package ru.iav.std.algorithms.p2.w1;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import static java.util.Arrays.stream;

@SuppressWarnings("unused")
public class Graph {

    private final int v;
    private final Bag<Integer>[] adj;

    /**
     * Create an empty graph with empty vs.
     *
     * @param v number of vs
     */
    @SuppressWarnings("unchecked")
    public Graph(int v) {
        this.v = v;
        adj = (Bag<Integer>[]) new Bag[v];
        vs().forEach(i -> adj[i] = new Bag<>());
    }

    /**
     * Create an empty graph from standard input.
     *
     * @param input standard input reader
     */
    @SuppressWarnings("unchecked")
    public Graph(In input) {
        this.v = input.readInt();
        int e = input.readInt();
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < e; i++) {
            addEdge(input.readInt(), input.readInt());
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * @param v given vertex
     * @return Vertices adjacent to V.
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public IntStream adjStream(int v) {
        return StreamSupport.stream(adj[v].spliterator(), false).mapToInt(Integer::intValue);
    }

    /**
     * @return Number of vs.
     */
    public int v() {
        return v;
    }

    /**
     * @return Number of edges.
     */
    public int e() {
        return vertices().mapToInt(Bag::size).sum();
    }

    public int degree(int v) {
        return (int) adjStream(v).count();
    }

    public int maxDegree() {
        return vs().map(this::degree).max().orElse(0);
    }

    public double avgDegree() {
        return 2.0 * e() / v();
    }

    public int numberOfSelfLoops() {
        return (int) vs().mapToLong(v -> adjStream(v).filter(w -> w == v).count()).sum() / 2;
    }

    private Stream<Bag<Integer>> vertices() {
        return stream(adj);
    }

    private IntStream vs() {
        return IntStream.range(0, v());
    }

    @Override
    public String toString() {
        return super.toString();    // TODO
    }
}
