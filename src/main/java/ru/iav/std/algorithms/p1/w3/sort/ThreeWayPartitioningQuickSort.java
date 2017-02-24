package ru.iav.std.algorithms.p1.w3.sort;

/**
 * Quick sort implementation for inputs containing multiple duplicate keys.
 */
public class ThreeWayPartitioningQuickSort extends QuickSort {

    @Override
    @SuppressWarnings("unchecked")
    protected void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int comp = a[i].compareTo(v);
            if (comp < 0)       swap(a, i++, lt++);
            else if (comp > 0)  swap(a, i, gt--);
            else                i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

}
