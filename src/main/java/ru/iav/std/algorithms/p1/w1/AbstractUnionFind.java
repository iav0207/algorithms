package ru.iav.std.algorithms.p1.w1;

/**
 * Created by takoe on 13.02.17.
 */
abstract class AbstractUnionFind {

    int[] id;

    AbstractUnionFind(int itemsCount) {
        id = new int[itemsCount];
        for (int i = 0; i < itemsCount; i++) {
            id[i] = i;
        }
    }

}
