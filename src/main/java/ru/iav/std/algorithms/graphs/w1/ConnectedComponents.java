package ru.iav.std.algorithms.graphs.w1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConnectedComponents {

    static int numberOfComponents(ArrayList<Integer>[] adj) {
        return new ComponentsExplorer(adj).count();
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }

    private static class ComponentsExplorer {

        private final ArrayList<Integer>[] adj;
        private Set<Integer> visited;
        private int cc;

        ComponentsExplorer(ArrayList<Integer>[] adj) {
            this.adj = adj;
        }

        int count() {
            initializeVisited();
            cc = 0;
            for (int u = 0; u < adj.length; u++) {
                if (isVisited(u)) continue;
                recursivelyExplore(u);
                cc++;
            }
            return cc;
        }

        private void initializeVisited() {
            visited = new HashSet<>();
        }

        private void recursivelyExplore(int u) {
            visit(u);
            adj[u].stream()
                    .filter(v -> u != v && !isVisited(v))
                    .forEach(this::recursivelyExplore);
        }

        private boolean isVisited(int v) {
            return visited.contains(v);
        }

        private void visit(int v) {
            visited.add(v);
        }

    }

}

