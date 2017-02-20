package ru.iav.std.algorithms.p1.w3.sort;

import static java.lang.Math.min;

/**
 * Created by takoe on 20.02.17.
 */
public class BottomUpMergeSort extends MergeSort {

    @Override
    protected void executeSorting(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int size = 1; size < n - 1; size *= 2) {
            for (int lo = 0; lo + size < n; lo += 2*size) {
                int mid = lo + size - 1;
                int hi = min(lo + 2*size - 1, n - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
    }

}
