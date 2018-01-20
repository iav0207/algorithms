package ru.iav.std.algorithms.p2.w1.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

import static java.util.Arrays.stream;

public class SAP {

    private static final int NO_SUCH_PATH = -1;

    private final Digraph g;
    private final Map<Pair<Integer>, SingleRunner> singleQueriesCache = new HashMap<>();
    private final Map<Pair<Iterable<Integer>>, MultiRunner> multiQueriesCache = new HashMap<>();

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
        return new SingleRunner(v, w);
//        return singleQueriesCache.computeIfAbsent(Pair.of(v, w), p -> new SingleRunner(v, w));
    }
    
    private class SingleRunner {
        final int v, w;
        int minLength = Integer.MAX_VALUE, ancestor = NO_SUCH_PATH;

        SingleRunner(int v, int w) {
            check(v, w);
            this.v = v;
            this.w = w;
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
        return new MultiRunner(v, w);
//        return multiQueriesCache.computeIfAbsent(Pair.of(v, w), p -> new MultiRunner(v, w));
    }

    private class MultiRunner {
        final Iterable<Integer> v, w;
        int minLength = Integer.MAX_VALUE, ancestor = NO_SUCH_PATH;

        MultiRunner(Iterable<Integer> v, Iterable<Integer> w) {
            check(v); check(w);
            this.v = v;
            this.w = w;
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
        if (streamOf(requireNonNull(vertices)).anyMatch(v -> v < 0 || v >= g.V())) {
            throw new IllegalArgumentException("Vertex of of bounds.");
        }
    }

    private void check(int... vertices) {
        if (stream(vertices).anyMatch(v -> v < 0 || v >= g.V())) {
            throw new IllegalArgumentException("Vertex of of bounds.");
        }
    }

    private static <T> Stream<T> streamOf(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    private static <T> T requireNonNull(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument is null.");
        }
        return obj;
    }

    private static class Pair<T> {
        final T a, b;

        static <E> Pair<E> of(E a, E b) {
            return new Pair<>(a, b);
        }

        Pair(T a, T b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?> pair = (Pair<?>) o;
            return (Objects.equals(a, pair.a) && Objects.equals(b, pair.b))
                    || (Objects.equals(a, pair.b) && Objects.equals(b, pair.a));
        }

        @Override
        public int hashCode() {
            return Objects.hash(((long) a.hashCode()) + b.hashCode());
        }
    }
}
