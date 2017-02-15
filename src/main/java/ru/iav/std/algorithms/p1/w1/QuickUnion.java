package ru.iav.std.algorithms.p1.w1;

/**
 * Created by takoe on 13.02.17.
 */
class QuickUnion extends AbstractUnionFind implements UnionFind {

    QuickUnion(int itemsCount) {
        super(itemsCount);
    }

    @Override
    public void union(int p, int q) {
        id[root(p)] = root(q);
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int p) {
        while (!isRoot(p)) {
            p = id[p];
        }
        return p;
    }

    private boolean isRoot(int p) {
        return id[p] == p;
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
