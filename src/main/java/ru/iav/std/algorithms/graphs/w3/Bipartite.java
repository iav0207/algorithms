package ru.iav.std.algorithms.graphs.w3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {

    private final List<Integer>[] adj;

    private Queue<Integer> queue;

    private Map<Integer, Boolean> colors;

    Bipartite(List<Integer>[] adj) {
        this.adj = adj;
        this.queue = new ArrayDeque<>(adj.length);
        this.colors = new HashMap<>(adj.length);
    }

    boolean isValidBipartite() {
        discover(0, false);
        try {
            while (!queue.isEmpty())
                process(queue.poll());
            return true;
        } catch (NotBipartiteException ex) {
            return false;
        }
    }

    private void process(int u) throws NotBipartiteException {
        Boolean uColor = getColor(u);
        for (Integer v : adj[u]) {
            Boolean vColor = getColor(v);
            if (uColor.equals(vColor))
                throw new NotBipartiteException();
            if (vColor == null)
                discover(v, !uColor);
        }
    }

    private void discover(int i, boolean color) {
        queue.add(i);
        setColor(i, color);
    }

    private Boolean getColor(int i) {
        return colors.get(i);
    }

    private void setColor(int i, boolean color) {
        colors.put(i, color);
    }

    private static int bipartite(ArrayList<Integer>[] adj) {
        return new Bipartite(adj).isValidBipartite() ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }

    private static class NotBipartiteException extends RuntimeException {}

}

