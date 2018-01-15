package ru.iav.std.algorithms.p2.w1;

public class ConnectedComponents {

    private final boolean[] visited;
    private final int[] id;
    private int count;

    public ConnectedComponents(Graph g) {
        id = new int[g.v()];
        visited = new boolean[g.v()];
        for (int v = 0; v < g.v(); v++) {
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
