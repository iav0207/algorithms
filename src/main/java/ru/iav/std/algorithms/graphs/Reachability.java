package ru.iav.std.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reachability {

    static int reach(ArrayList<Integer>[] adj, int x, int y) {
        return new Explorer(adj).explore(x).contains(y) ? 1 : 0;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = initializeAdjArray(n);
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }

    @SuppressWarnings("unchecked")
    static ArrayList<Integer>[] initializeAdjArray(int n) {
        return  Stream.generate(ArrayList<Integer>::new).limit(n)
                .collect(Collectors.toList()).toArray((ArrayList<Integer>[]) new ArrayList[n]);
    }

    private static class Explorer {

        private final ArrayList<Integer>[] adj;
        private Set<Integer> visited;

        Explorer(ArrayList<Integer>[] adj) {
            this.adj = adj;
            visited = new HashSet<>();
        }

        Set<Integer> explore(int u) {
            if (u < 0 || u >= adj.length) throw new IllegalArgumentException("No such vertex in the graph");
            recursivelyExplore(u);
            return resultAsDefensiveCopy();
        }

        private void recursivelyExplore(int u) {
            visit(u);
            adj[u].stream()
                    .filter(v -> u != v)
                    .filter(v -> !isVisited(v))
                    .forEach(this::recursivelyExplore);
        }

        private Set<Integer> resultAsDefensiveCopy() {
            return new HashSet<>(visited);
        }

        private boolean isVisited(int v) {
            return visited.contains(v);
        }

        private void visit(int v) {
            visited.add(v);
        }

    }

}

