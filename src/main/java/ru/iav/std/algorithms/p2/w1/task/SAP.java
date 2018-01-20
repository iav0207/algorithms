package ru.iav.std.algorithms.p2.w1.task;

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
    private final Map<Integer, Map<Integer, SingleRunner>> srCache = new HashMap<>();

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
        return singleRunner(v, w).minLength;
    }

    /**
     * A common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     */
    public int ancestor(int v, int w) {
        return singleRunner(v, w).ancestor;
    }

    private SingleRunner singleRunner(int v, int w) {
        int a = Math.min(v, w), b = Math.max(v, w);
        srCache.putIfAbsent(a, new HashMap<>());
        return srCache.get(a).computeIfAbsent(b, ignore -> new SingleRunner(a, b));
    }
    
    private class SingleRunner {
        int minLength = Integer.MAX_VALUE, ancestor = NO_SUCH_PATH;

        SingleRunner(int v, int w) {
            check(v, w);
            if (v == w) {
                minLength = 0;
                ancestor = v;
                return;
            }
            BreadthFirstDirectedPaths vBfs = new BreadthFirstDirectedPaths(g, v);
            BreadthFirstDirectedPaths wBfs = new BreadthFirstDirectedPaths(g, w);
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new Queue<>();
            queue.enqueue(v);
            queue.enqueue(w);
            while (!queue.isEmpty()) {
                final int c = queue.dequeue();
                final int vDist = vBfs.distTo(c), wDist = wBfs.distTo(c);
                visited.add(c);
                if (vDist > minLength || wDist > minLength) {
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

    /**
     * Length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return multiRunner(v, w).minLength;
    }

    /**
     * A common ancestor that participates in shortest ancestral path; -1 if no such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return multiRunner(v, w).ancestor;
    }

    private MultiRunner multiRunner(Iterable<Integer> v, Iterable<Integer> w) {
        // mb cache?
        return new MultiRunner(v, w);
    }

    private class MultiRunner {
        int minLength = Integer.MAX_VALUE, ancestor = NO_SUCH_PATH;

        MultiRunner(Iterable<Integer> v, Iterable<Integer> w) {
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
                if (vDist > minLength || wDist > minLength) {
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

    private void check(int... vertices) {
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
