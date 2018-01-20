package ru.iav.std.algorithms.p2.w2;

public class Edge implements Comparable<Edge> {

    private final int v, w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(int v) {
        return v == this.v ? w : v;
    }

    public boolean adjacentTo(int v) {
        return v == this.v || v == this.w;
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }
}
