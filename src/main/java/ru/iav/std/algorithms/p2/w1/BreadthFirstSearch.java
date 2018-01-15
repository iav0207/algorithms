package ru.iav.std.algorithms.p2.w1;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import static java.util.Collections.emptyList;

public class BreadthFirstSearch {

    private final boolean visited[];
    private final int[] edgeTo;
    private final int[] distTo;
    private final int s;

    public BreadthFirstSearch(Graph graph, int source) {
        edgeTo = new int[graph.v()];
        distTo = new int[graph.v()];
        visited = new boolean[graph.v()];
        s = source;
        bfs(graph, source);
    }

    private void bfs(Graph g, int s) {
        visited[s] = true;
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        int count = 0;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            if (visited[v]) {
                continue;
            }
            count++;
            for (int w : g.adj(v)) {
                visited[w] = true;
                edgeTo[w] = v;
                distTo[w] = count;
            }
        }
    }

    public boolean hasPathTo(int v) {
        return v >= 0 && v < visited.length && visited[v];
    }

    public int distTo(int v) {
        return visited[v] ? Integer.MAX_VALUE : distTo[v];
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
