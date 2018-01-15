package ru.iav.std.algorithms.p2.w1;

import edu.princeton.cs.algs4.Stack;

/**
 * Topological sorting.
 */
public class DepthFirstOrder {

    private final boolean[] visited;
    private final Stack<Integer> reversePostorder;

    public DepthFirstOrder(Digraph g) {
        reversePostorder = new Stack<>();
        visited = new boolean[g.v()];
        for (int v = 0; v < g.v(); v++) {
            if (!visited[v]) {
                dfs(g, v);
            }
        }
    }

    public Iterable<Integer> reversePost() {
        return reversePostorder;
    }

    private void dfs(Digraph g, int v) {
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(g, w);
            }
        }
        reversePostorder.push(v);
    }
}
