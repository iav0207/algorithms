package ru.iav.std.algorithms.graphs.w2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class StronglyConnectedTimingPassedVersion {

    private static final boolean debugMode = false;

    private final ArrayList<Integer>[] adj, reverse;

    private Set<Integer> visited, excluded;

    private Stack<Integer> order;

    private int scc;

    private StronglyConnectedTimingPassedVersion(ArrayList<Integer>[] adj, ArrayList<Integer>[] reverse) {
        this.adj = adj;
        this.reverse = reverse;
        order = new Stack<>();
        visited = new HashSet<>();
        excluded = new HashSet<>();
    }

    private int count() {
        exploreReverse();
        forEachVertexFromStackExploreAndExcludeAllAndIncrementScc();
        return scc;
    }

    private void exploreReverse() {
        for (int u = 0; u < reverse.length; u++)
            if (!isVisited(u)) exploreReverse(u);
    }

    private void exploreReverse(int u) {
        visit(u);
        for (Integer v : reverse[u])
            if (!isVisited(v)) exploreReverse(v);
        order.push(u);
    }

    private void forEachVertexFromStackExploreAndExcludeAllAndIncrementScc() {
        while (!order.isEmpty()) {
            int u = order.pop();
            if (!isExcluded(u))
                exploreScc(u);
        }
    }

    private void exploreScc(int u) {
        recursivelyExploreScc(u);
        scc++;
    }

    private void recursivelyExploreScc(int u) {
        exclude(u);
        for (Integer v : adj[u])
            if (!isExcluded(v)) recursivelyExploreScc(v);
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
        return new StronglyConnectedTimingPassedVersion(adj, buildReverseGraph(adj)).count();
    }

    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, ArrayList<Integer>[] rev) {
        return new StronglyConnectedTimingPassedVersion(adj, rev).count();
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

    private static ArrayList<Integer>[] buildReverseGraph(ArrayList<Integer>[] graph) {
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

