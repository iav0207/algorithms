package ru.iav.std.algorithms.p1.w1;

/**
 * Created by takoe on 12.02.17.
 */
public interface UnionFind {

    void union(int p, int q);

    boolean connected(int p, int q);

    int find(int p);

    int count();

}
