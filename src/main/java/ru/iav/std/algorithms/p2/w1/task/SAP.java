package ru.iav.std.algorithms.p2.w1.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

public class SAP {

    private static final int NO_SUCH_PATH = -1;

    private final Digraph g;
    private final Map<Integer, Map<Integer, Runner>> queryCache = new HashMap<>();

    /**
     * Constructor takes a digraph (not necessarily a DAG)
     */
    public SAP(Digraph g) {
        this.g = new Digraph(g);
    }

    /**
     * Length of shortest ancestral path between v and w; -1 if no such path
     */
    public int length(int v, int w) {
        return runner(v, w).minLength;
    }

    /**
     * A common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     */
    public int ancestor(int v, int w) {
        return runner(v, w).ancestor;
    }

    private Runner runner(int v, int w) {
        int a = Math.min(v, w), b = Math.max(v, w);
        queryCache.putIfAbsent(a, new HashMap<>());
        return queryCache.get(a).computeIfAbsent(b, ignore -> new Runner(a, b));
    }

    /**
     * Length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return newRunner(v, w).minLength;
    }

    /**
     * A common ancestor that participates in shortest ancestral path; -1 if no such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return newRunner(v, w).ancestor;
    }

    private Runner newRunner(Iterable<Integer> v, Iterable<Integer> w) {
        // mb cache?
        return new Runner(v, w);
    }

    private class Runner {
        int minLength = Integer.MAX_VALUE, ancestor = NO_SUCH_PATH;
        
        Runner(int v, int w) {
            this(Collections.singletonList(v), Collections.singletonList(w));
        }

        Runner(Iterable<Integer> v, Iterable<Integer> w) {
            check(v);
            check(w);
            BreadthFirstDirectedPaths vBfs = new BreadthFirstDirectedPaths(g, v);
            BreadthFirstDirectedPaths wBfs = new BreadthFirstDirectedPaths(g, w);
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new Queue<>();
            v.forEach(queue::enqueue);
            w.forEach(queue::enqueue);
            while (!queue.isEmpty()) {
                final int c = queue.dequeue();
                final int vDist = vBfs.distTo(c), wDist = wBfs.distTo(c);
                visited.add(c);
                if (vDist > minLength && wDist > minLength) {
                    continue;
                }
                if (vBfs.hasPathTo(c) && wBfs.hasPathTo(c)) {
                    int length = vDist + wDist;
                    if (length < minLength) {
                        ancestor = c;
                        minLength = length;
                    }
                }
                for (int s : g.adj(c)) if (!visited.contains(s)) queue.enqueue(s);
            }
            if (minLength == Integer.MAX_VALUE) minLength = NO_SUCH_PATH;
        }
    }

    private void check(Iterable<Integer> vertices) {
        for (int v : requireNonNull(vertices)) {
            if (v < 0 || v >= g.V()) {
                throw new IllegalArgumentException("Vertex of of bounds.");
            }
        }
    }

    private static <T> T requireNonNull(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument is null.");
        }
        return obj;
    }
}
