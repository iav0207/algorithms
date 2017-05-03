package ru.iav.std.algorithms.graphs.w4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

;

@SuppressWarnings("unchecked")
public class NegativeCycle {

    private static final int UNREACHABLE = -1;

    private static final int HEAVY_WEIGHT = Integer.MAX_VALUE / 10;

    /** [node]: adjacent node -> cost to reach it */
    private final Map<Integer, Integer>[] maps;

    /** node -> dist */
    private Map<Integer, Integer> dist;

    /** node -> prev */
    private LinkedHashMap<Integer, Integer> prev;

    NegativeCycle(List<Integer>[] adj, List<Integer>[] cost) {
        maps = (Map<Integer, Integer>[]) new Map[adj.length];
        for (int i = 0; i < adj.length; i++) {
            int size = adj[i].size();
            maps[i] = new HashMap<>(size);
            for (int j = 0; j < size; j++) {
                maps[i].put(adj[i].get(j), cost[i].get(j));
            }
        }
    }

    boolean hasNegativeCycle() {
        this.dist = new HashMap<>();
        this.prev = new LinkedHashMap<>();
        dist.put(0, 0);
        for (int i = 0; i < maps.length; i++)   // running Bellman-Ford V times
            runBellmanFord();
        return isInTheCycle(getLastUpdatedVertex());
    }

    private void runBellmanFord() {
        IntStream.range(0, maps.length).forEach(this::processVertex);
    }

    private boolean isInTheCycle(final Integer lastRelaxedVertex) {
        if (lastRelaxedVertex == null)
            return false;
        Integer x = prev.get(lastRelaxedVertex);
        while (x != null) {
            if (x.equals(lastRelaxedVertex))
                return true;    // cycled
            x = prev.get(x);
        }
        return false;
    }

    private Integer getLastUpdatedVertex() {
        Iterator<Integer> iterator = prev.keySet().iterator();
        int v = -1;
        while (iterator.hasNext())
            v = iterator.next();
        return v == -1 ? null : v;
    }

    private void processVertex(int u) {
        maps[u].keySet().forEach(v -> relax(u, v));
    }

    private void relax(int u, int v) {
        int w = edgeWeight(u, v);
        int uDist = getDist(u);
        int vDist = getDist(v);
        if (vDist == UNREACHABLE || vDist > uDist + w) {
            setDist(v, uDist + w);
            prev.put(v, u);
        }
    }

    private Integer edgeWeight(int u, int v) {
        return maps[u].containsKey(v) ? maps[u].get(v) : HEAVY_WEIGHT;
    }

    private int getDist(int u) {
        return dist.containsKey(u) ? dist.get(u) : UNREACHABLE;
    }

    private void setDist(int u, int d) {
        dist.put(u, d);
    }

    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        return new NegativeCycle(adj, cost).hasNegativeCycle() ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

