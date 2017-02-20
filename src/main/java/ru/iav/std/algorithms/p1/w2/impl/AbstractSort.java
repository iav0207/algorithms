package ru.iav.std.algorithms.p1.w2.impl;

import ru.iav.std.algorithms.p1.w2.Sort;

/**
 * Created by takoe on 20.02.17.
 */
abstract class AbstractSort  implements Sort {

    @Override
    public void sort(Comparable[] a) {
        if (a == null || a.length < 2) return;
        executeSorting(a);
    }

    protected abstract void executeSorting(Comparable[] a);

    boolean less(Comparable first, Comparable second) {
        return first.compareTo(second) < 0;
    }

    void swap(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
