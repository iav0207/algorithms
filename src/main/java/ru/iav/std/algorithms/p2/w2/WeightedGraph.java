package ru.iav.std.algorithms.p2.w2;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import static java.util.Arrays.stream;

@SuppressWarnings("unused")
public class WeightedGraph {

    private final int v;
    final Bag<Edge>[] adj;

    /**
     * Create an empty weighted graph with empty vs.
     *
     * @param v number of vs
     */
    @SuppressWarnings("unchecked")
    public WeightedGraph(int v) {
        this.v = v;
        adj = (Bag<Edge>[]) new Bag[v];
        vs().forEach(i -> adj[i] = new Bag<>());
    }

    /**
     * Create an empty weighted graph from standard input.
     *
     * @param input standard input reader
     */
    @SuppressWarnings("unchecked")
    public WeightedGraph(In input) {
        this.v = input.readInt();
        int e = input.readInt();
        adj = (Bag<Edge>[]) new Bag[v];
        for (int i = 0; i < e; i++) {
            addEdge(new Edge(input.readInt(), input.readInt(), input.readDouble()));
        }
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }

    /**
     * @param v given vertex
     * @return Edges of V.
     */
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Stream<Edge> adjStream(int v) {
        return StreamSupport.stream(adj[v].spliterator(), false);
    }

    public Stream<Edge> edges() {
        return vertices().flatMap(bag -> StreamSupport.stream(bag.spliterator(), false));
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
        return (int) vs().mapToLong(v -> adjStream(v).filter(e -> e.adjacentTo(v)).count()).sum() / 2;
    }

    private Stream<Bag<Edge>> vertices() {
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
