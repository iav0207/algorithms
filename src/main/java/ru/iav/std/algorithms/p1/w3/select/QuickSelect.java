package ru.iav.std.algorithms.p1.w3.select;

import edu.princeton.cs.algs4.StdRandom;
import ru.iav.std.algorithms.p1.w3.sort.QuickSort;

/**
 * Finds the k-th largest element in the array taking linear time.
 */
public class QuickSelect {

    private QuickSort quickSort = new QuickSort();

    public Comparable select(Comparable[] a, final int k) {
        validate(a, k);
        StdRandom.shuffle(a);
        int lo = 0;
        int hi = a.length - 1;
        while (hi > lo) {
            int j = quickSort.partition(a, lo, hi);
            if (j < k)          lo = j + 1;
            else if (j > k)     hi = j - 1;
            else                return a[k];
        }
        return a[k];
    }

    private void validate(Comparable[] a, int k) {
        if (a == null || a.length < 1 || k < 1 || k > a.length)
            throw new IllegalArgumentException();
    }

}
