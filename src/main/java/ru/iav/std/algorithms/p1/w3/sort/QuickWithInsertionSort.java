package ru.iav.std.algorithms.p1.w3.sort;

import ru.iav.std.algorithms.p1.w2.sort.InsertionSort;

/**
 * Created by takoe on 24.02.17.
 */
public class QuickWithInsertionSort extends QuickSort {

    public static int CUTOFF = 10;

    private InsertionSort insertion = new InsertionSort();

    @Override
    protected void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        if (hi - lo < CUTOFF)
            insertion.sort(a, lo, hi + 1);
        else
            super.sort(a, lo, hi);
    }

}
