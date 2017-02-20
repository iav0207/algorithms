package ru.iav.std.algorithms.p1.w2.impl;

/**
 * Created by takoe on 20.02.17.
 */
public class SelectionSort extends AbstractSort {

    @Override
    protected void executeSorting(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (a[j] == null) continue;
                if (less(a[j], a[min])) min = j;
            }
            swap(a, min, i);
        }
    }

}
