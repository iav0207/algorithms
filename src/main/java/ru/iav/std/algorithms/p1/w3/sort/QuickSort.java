package ru.iav.std.algorithms.p1.w3.sort;

import edu.princeton.cs.algs4.StdRandom;
import ru.iav.std.algorithms.p1.w2.sort.AbstractSort;

/**
 * Created by takoe on 24.02.17.
 */
public class QuickSort extends AbstractSort {

    @Override
    protected void executeSorting(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    protected void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) break;
            }
            while (less(a[lo], a[--j])) {
                if (j == lo) break;
            }
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }
}
