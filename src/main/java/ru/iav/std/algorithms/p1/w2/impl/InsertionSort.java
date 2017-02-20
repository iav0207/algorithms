package ru.iav.std.algorithms.p1.w2.impl;

/**
 * Created by takoe on 20.02.17.
 */
public class InsertionSort extends AbstractSort {

    @Override
    protected void executeSorting(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                swap(a, j, j - 1);
    }

}
