package ru.iav.std.algorithms.p1.w2.sort;

import ru.iav.std.algorithms.p1.w2.Sort;

/**
 * Created by takoe on 20.02.17.
 */
public abstract class AbstractSort  implements Sort {

    @Override
    public void sort(Comparable[] a) {
        if (a == null || a.length < 2) return;
        executeSorting(a);
    }

    protected abstract void executeSorting(Comparable[] a);

    protected boolean less(Comparable first, Comparable second) {
        if (first == null) return false;
        if (second == null) return true;
        return first.compareTo(second) < 0;
    }

    protected void swap(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
