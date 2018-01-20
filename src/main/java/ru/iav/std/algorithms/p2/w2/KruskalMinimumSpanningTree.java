package ru.iav.std.algorithms.p2.w2;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * Kruskal's algorithm. Runtime complexity: O(E*log(E))
 */
public class KruskalMinimumSpanningTree {

    private final Queue<Edge> mst;

    public KruskalMinimumSpanningTree(WeightedGraph g) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        UF uf = new UF(g.v());
        g.edges().forEach(pq::insert);
        while (!pq.isEmpty() && mst.size() < g.v() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
            }
        }
    }

    public Iterable<Edge> mst() {
        return mst;
    }
}
