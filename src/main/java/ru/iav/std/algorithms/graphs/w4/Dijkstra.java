package ru.iav.std.algorithms.graphs.w4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Dijkstra {

    private static final int UNREACHABLE = -1;

    private static final int HEAVY_WEIGHT = Integer.MAX_VALUE / 10;

    /** [node]: adjacent node -> cost to reach it */
    private final Map<Integer, Integer>[] maps;

    /** starting node */
    private final int s;

    /** node -> dist */
    private Map<Integer, Integer> dist;

    /** dist -> nodes, sorted */
    private TreeMap<Integer, Set<Integer>> distPQ;

    @SuppressWarnings("unchecked")
    Dijkstra(List<Integer>[] adj, List<Integer>[] cost, int s) {
        this.s = s;
        this.maps = (Map<Integer, Integer>[]) new Map[adj.length];
        for (int i = 0; i < adj.length; i++) {
            int size = adj[i].size();
            maps[i] = new HashMap<>(size);
            for (int j = 0; j < size; j++) {
                maps[i].put(adj[i].get(j), cost[i].get(j));
            }
        }
        runDijkstra();
    }

    private void runDijkstra() {
        this.dist = new HashMap<>();
        this.distPQ = new TreeMap<>();
        dist.put(s, 0);
        enqueue(s, 0);
        while (!distPQ.isEmpty())
            process(dequeueMin());
    }

    private void process(int u) {
        maps[u].keySet().forEach(v -> relax(u, v));
    }

    private void relax(int u, int v) {
        int w = edgeWeight(u, v);
        int uDist = getDist(u);
        int vDist = getDist(v);
        if (vDist == UNREACHABLE || vDist > uDist + w) {
            setDist(v, uDist + w);
            changePriority(v, vDist, getDist(v));
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

    private void enqueue(int u, int d) {
        if (!distPQ.containsKey(d))
            distPQ.put(d, new HashSet<>());
        distPQ.get(d).add(u);
    }

    private int dequeueMin() {
        Map.Entry<Integer, Set<Integer>> min = distPQ.pollFirstEntry();
        Set<Integer> nodesOfMinDist = min.getValue();
        int u = nodesOfMinDist.iterator().next();
        nodesOfMinDist.remove(u);
        if (!nodesOfMinDist.isEmpty())
            distPQ.put(min.getKey(), nodesOfMinDist);
        return u;
    }

    private void changePriority(int u, int dOld, int dNew) {
        Set<Integer> set = distPQ.remove(dOld);
        if (set != null) {
            set.remove(u);
            if (!set.isEmpty())
                distPQ.put(dOld, set);
        }
        enqueue(u, dNew);
    }

    int distanceTo(int t) {
        return getDist(t);
    }

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        return new Dijkstra(adj, cost, s).distanceTo(t);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

