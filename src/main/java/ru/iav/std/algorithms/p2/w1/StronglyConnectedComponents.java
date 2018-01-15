package ru.iav.std.algorithms.p2.w1;

/**
 * Kosaraju-Sharir algorithm for finding SCCs in digraphs.
 */
public class StronglyConnectedComponents {

    private final boolean[] visited;
    private final int[] id;
    private int count;

    public StronglyConnectedComponents(Digraph g) {
        id = new int[g.v()];
        visited = new boolean[g.v()];
        DepthFirstOrder toposort = new DepthFirstOrder(g.reverse());
        for (int v : toposort.reversePost()) {
            if (!visited[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    private void dfs(Graph g, int v) {
        id[v] = count;
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }
}
