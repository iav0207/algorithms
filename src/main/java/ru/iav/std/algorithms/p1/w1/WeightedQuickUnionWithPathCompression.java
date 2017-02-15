package ru.iav.std.algorithms.p1.w1;

/**
 * Created by takoe on 13.02.17.
 */
class WeightedQuickUnionWithPathCompression extends WeightedQuickUnion {

    WeightedQuickUnionWithPathCompression(int itemsCount) {
        super(itemsCount);
    }

    @Override
    int root(int p) {
        while (!isRoot(p)) {
            id[p] = grandparent(p);
            p = parent(p);
        }
        return p;
    }

    private int grandparent(int p) {
        return parent(parent(p));
    }

}
