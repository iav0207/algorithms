package ru.iav.std.algorithms.p1.w2.impl;

import ru.iav.std.algorithms.p1.w2.Sort;

/**
 * Created by takoe on 20.02.17.
 */
public class SelectionSort implements Sort {

    public void sort(Comparable[] a) {
        if (a == null || a.length < 2) return;
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

    private boolean less(Comparable first, Comparable second) {
        return first.compareTo(second) < 0;
    }

    private void swap(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
