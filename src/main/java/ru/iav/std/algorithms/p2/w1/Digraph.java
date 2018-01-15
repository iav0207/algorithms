package ru.iav.std.algorithms.p2.w1;

import edu.princeton.cs.algs4.In;

@SuppressWarnings("unused")
public class Digraph extends Graph {

    public Digraph(int v) {
        super(v);
    }

    public Digraph(In input) {
        super(input);
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public Digraph reverse() {
        Digraph rev = new Digraph(v());
        for (int v = 0; v < v(); v++) {
            for (int w : adj(v)) {
                rev.addEdge(w, v);
            }
        }
        return rev;
    }
}
