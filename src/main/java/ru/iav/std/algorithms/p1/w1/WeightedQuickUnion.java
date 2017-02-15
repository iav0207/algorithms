package ru.iav.std.algorithms.p1.w1;

/**
 * Created by takoe on 13.02.17.
 */
class WeightedQuickUnion extends AbstractUnionFind implements UnionFind {

    int[] size;

    WeightedQuickUnion(int itemsCount) {
        super(itemsCount);
        size = new int[itemsCount];
        for (int i = 0; i < itemsCount; i++) {
            size[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) return;
        if (size[rootP] > size[rootQ]) {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    int root(int p) {
        while (!isRoot(p)) {
            p = parent(p);
        }
        return p;
    }

    boolean isRoot(int p) {
        return id[p] == p;
    }

    int parent(int p) {
        return id[p];
    }

    @Override
    public int find(int p) {
        return 0;
    }

    @Override
    public int count() {
        return 0;
    }
}
