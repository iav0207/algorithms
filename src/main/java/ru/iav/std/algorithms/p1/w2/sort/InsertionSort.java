package ru.iav.std.algorithms.p1.w2.sort;

/**
 * Created by takoe on 20.02.17.
 */
public class InsertionSort extends AbstractSort {

    @Override
    protected void executeSorting(Comparable[] a) {
        sort(a, 0, a.length);
    }

    /**
     * Sort a subarray of given array with insertion sort algorithm.
     * @param a a given array of {@link Comparable} items.
     * @param lo range start (inclusive).
     * @param hi range end (exclusive).
     */
    public void sort(Comparable[] a, int lo, int hi) {
        if (a == null || hi - lo < 2) return;
        for (int i = lo + 1; i < hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                swap(a, j, j - 1);
    }

}
