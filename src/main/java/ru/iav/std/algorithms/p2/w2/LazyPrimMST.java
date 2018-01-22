package ru.iav.std.algorithms.p2.w2;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {

    private final MinPQ<Edge> pq = new MinPQ<>();
    private final Queue<Edge> mst = new Queue<>();
    private boolean[] visited;

    public LazyPrimMST(WeightedGraph g) {
        visited = new boolean[g.v()];
        visit(g, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (visited[v] && visited[w])
                continue;
            mst.enqueue(e);
            if (!visited[v]) visit(g, v);
            if (!visited[w]) visit(g, w);
        }
    }

    private void visit(WeightedGraph g, int v) {
        visited[v] = true;
        for (Edge e : g.adj(v)) {
            if (!visited[e.other(v)]) pq.insert(e);
        }
    }

    public Iterable<Edge> mst() {
        return mst;
    }
}
