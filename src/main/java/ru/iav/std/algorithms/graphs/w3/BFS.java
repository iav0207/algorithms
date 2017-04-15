package ru.iav.std.algorithms.graphs.w3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

    private static final int UNREACHABLE = -1;

    private final List<Integer>[] adj;

    private final Integer s;

    private Queue<Integer> queue;

    private Map<Integer, Integer> dist;

    BFS(List<Integer>[] adj, int s) {
        this.adj = adj;
        this.s = s;

        queue = new ArrayDeque<>(adj.length);
        dist = new HashMap<>(adj.length);

        runBfs();
    }

    int distanceTo(int t) {
        return getDist(t);
    }

    private void runBfs() {
        discover(s, 0);
        while (!queue.isEmpty())
            process(queue.poll());
    }

    private void process(Integer u) {
        adj[u].stream()
                .filter(v -> !dist.containsKey(v))
                .forEach(v -> discover(v, getDist(u) + 1));
    }

    private void discover(int i, int dist) {
        queue.add(i);
        setDist(i, dist);
    }

    private void setDist(int i, int d) {
        dist.put(i, d);
    }

    private Integer getDist(int i) {
        return dist.get(i) == null ? UNREACHABLE : dist.get(i);
    }

    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        return new BFS(adj, s).distanceTo(t);
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
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

