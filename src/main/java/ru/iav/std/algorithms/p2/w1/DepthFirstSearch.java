package ru.iav.std.algorithms.p2.w1;


import edu.princeton.cs.algs4.Stack;

import static java.util.Collections.emptyList;

public class DepthFirstSearch {

    private final boolean visited[];
    private final int[] edgeTo;
    private final int s;

    public DepthFirstSearch(Graph graph, int source) {
        edgeTo = new int[graph.v()];
        visited = new boolean[graph.v()];
        s = source;
        dfs(graph, source);
    }

    private void dfs(Graph g, int v) {
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return v >= 0 && v < visited.length && visited[v];
    }

    @SuppressWarnings("Duplicates")
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return emptyList();
        }
        Stack<Integer> path = new Stack<>();
        for (int e = edgeTo[v]; e != s; e = edgeTo[e]) {
            path.push(e);
        }
        path.push(s);
        return path;
    }
}
