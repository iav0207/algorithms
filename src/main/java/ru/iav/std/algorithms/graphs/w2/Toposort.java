package ru.iav.std.algorithms.graphs.w2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Toposort {

    private final ArrayList<Integer>[] adj;

    private Set<Integer> visited;

    private Stack<Integer> order;

    private Toposort(ArrayList<Integer>[] adj) {
        this.adj = adj;
    }

    private List<Integer> linearize() {
        visited = new HashSet<>();
        order = new Stack<>();
        IntStream.range(0, adj.length)
                .filter(u -> !isVisited(u))
                .forEach(this::linearize);
        return Stream.generate(order::pop)
                .limit(order.size())
                .collect(toList());
    }

    private void linearize(int u) {
        visit(u);
        adj[u].stream()
                .filter(v -> !isVisited(v))
                .forEach(this::linearize);
        order.push(u);
    }

    private boolean isVisited(int v) {
        return visited.contains(v);
    }

    private void visit(int v) {
        visited.add(v);
    }

    static List<Integer> toposort(ArrayList<Integer>[] adj) {
        return new Toposort(adj).linearize();
    }

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
        }
        List<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }

}

