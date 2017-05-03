package ru.iav.std.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by takoe on 15.04.17.
 */
public class DirectedWeightedGraph<W extends Number> {

    private final List<Integer>[] adj;

    private final List<W>[] weights;

    public DirectedWeightedGraph(List<Integer>[] adj, List<W>[] weights) {
        this.adj = adj;
        this.weights = weights;
    }

    public List<Integer>[] getAdj() {
        return adj;
    }

    public List<W>[] getWeights() {
        return weights;
    }

    public static class Builder<W extends Number> {

        private final Set<Integer> nodes;

        private final Map<Integer, Map<Integer, W>> maps;

        Builder() {
            nodes = new HashSet<>();
            maps = new HashMap<>();
        }

        public Builder addEdge(int u, int v, W weight) {
            u--;
            v--;
            nodes.add(u);
            nodes.add(v);
            if (!maps.containsKey(u))
                maps.put(u, new LinkedHashMap<>());
            maps.get(u).put(v, weight);
            return this;
        }

        @SuppressWarnings("unchecked")
        public DirectedWeightedGraph<W> build() {

            List<Integer>[] adj = (List<Integer>[]) new List[nodes.size()];
            List<W>[] weights = (List<W>[]) new List[nodes.size()];

            for (int u = 0; u < adj.length; u++) {
                adj[u] = new ArrayList<>();
                weights[u] = new ArrayList<>();

                if (!maps.containsKey(u))
                    continue;

                for (Map.Entry<Integer, W> e : maps.get(u).entrySet()) {
                    adj[u].add(e.getKey());
                    weights[u].add(e.getValue());
                }
            }
            return new DirectedWeightedGraph<>(adj, weights);
        }

    }
}
