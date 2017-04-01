package ru.iav.std.algorithms.graphs.w2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Acyclicity {

    static int acyclic(ArrayList<Integer>[] adj) {
        return new CycleFinder(adj).isCyclic() ? 1 : 0;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = Stream.generate(ArrayList<Integer>::new).limit(n)
                .collect(Collectors.toList()).toArray((ArrayList<Integer>[]) new ArrayList[n]);
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }

    private static class CycleFinder {

        private final ArrayList<Integer>[] adj;
        private Set<Integer> visited;

        CycleFinder(ArrayList<Integer>[] adj) {
            this.adj = adj;
        }

        boolean isCyclic() {
            initializeVisited();
            for (int u = 0; u < adj.length; u++) {
                if (isVisited(u)) continue;
                if (hasCycle(u)) return true;
            }
            return false;
        }

        private void initializeVisited() {
            visited = new HashSet<>();
        }

        private boolean hasCycle(int u) {
            visit(u);
            Set<Integer> exploredFromU = new HashSet<>();
            exploredFromU.add(u);
            for (Integer eachEdge : adj[u]) {
                if (isVisited(eachEdge)) continue;
                if (hasCycle(eachEdge, exploredFromU)) return true;
            }
            return false;
        }

        private boolean hasCycle(int w, Set<Integer> explored) {
            if (explored.contains(w)) return true;
            visit(w);
            explored.add(w);
            for (Integer edge : adj[w]) {
                if (hasCycle(edge, explored)) return true;
            }
            explored.remove(w);
            return false;
        }

        private boolean isVisited(int v) {
            return visited.contains(v);
        }

        private void visit(int v) {
            visited.add(v);
        }

    }

}

