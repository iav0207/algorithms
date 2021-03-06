package ru.iav.std.algorithms.graphs.w2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class StronglyConnected {

    private static final boolean debugMode = false;

    private final ArrayList<Integer>[] adj, reverse;

    private Set<Integer> visited, excluded;

    private Stack<Integer> order;

    private int scc;

    private StronglyConnected(ArrayList<Integer>[] adj, ArrayList<Integer>[] reverse) {
        this.adj = adj;
        this.reverse = reverse;
        order = new Stack<>();
        visited = new HashSet<>();
        excluded = new HashSet<>();
    }

    private int count() {
        log("\nSCC analysis starts.");
        exploreReverse();
        forEachVertexFromStackExploreAndExcludeAllAndIncrementScc();
        log("Done.\n");
        return scc;
    }

    private void exploreReverse() {
        IntStream.range(0, reverse.length)
                .filter(u -> !isVisited(u))
                .forEach(this::exploreReverse);
    }

    private void exploreReverse(int u) {
        visit(u);
        reverse[u].stream()
                .filter(v -> !isVisited(v))
                .forEach(this::exploreReverse);
        log("Added to order: " + u);
        order.push(u);
    }

    private void forEachVertexFromStackExploreAndExcludeAllAndIncrementScc() {
        Stream.generate(order::pop).limit(order.size())
                .filter(u -> u != null)         .peek(u -> log("Trying to poll: %d", u))
                .filter(u -> !isExcluded(u))    .peek(u -> log("Starting to explore SCC from vertex %d", u))
                .forEach(this::exploreScc);
    }

    private void exploreScc(int u) {
        recursivelyExploreScc(u);
        scc++;
        log("The whole SCC excluded: #" + scc);
    }

    private void recursivelyExploreScc(int u) {
        log("Visiting and excluding " + u);
        exclude(u);
        adj[u].stream()
                .filter(v -> !isExcluded(v))
                .forEach(this::recursivelyExploreScc);
    }

    private boolean isExcluded(int v) {
        return excluded.contains(v);
    }

    private void exclude(int v) {
        excluded.add(v);
    }

    private boolean isVisited(int v) {
        return visited.contains(v);
    }

    private void visit(int v) {
        visited.add(v);
    }

    static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        return new StronglyConnected(adj, buildReverseGraph(adj)).count();
    }

    static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, ArrayList<Integer>[] rev) {
        return new StronglyConnected(adj, rev).count();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = initializeAdjArray(n);
        ArrayList<Integer>[] rev = initializeAdjArray(n);
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            rev[y - 1].add(x - 1);  // building a reverse graph
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, rev));
    }

    private static ArrayList<Integer>[] initializeAdjArray(int n) {
        return Stream.generate(ArrayList<Integer>::new).limit(n)
                .collect(Collectors.toList()).toArray((ArrayList<Integer>[]) new ArrayList[n]);
    }

    static ArrayList<Integer>[] buildReverseGraph(ArrayList<Integer>[] graph) {
        ArrayList<Integer>[] reverseGraph = initializeAdjArray(graph.length);
        for (int u = 0; u < reverseGraph.length; u++)
            for (Integer v : graph[u])
                reverseGraph[v].add(u);
        return reverseGraph;
    }

    private static void log(String format, Object... objects) {
        if (debugMode) log(String.format(format, objects));
    }

    private static void log(String message) {
        if (debugMode) System.out.println(message);
    }

}

