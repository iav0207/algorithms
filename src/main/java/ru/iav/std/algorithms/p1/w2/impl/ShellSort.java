package ru.iav.std.algorithms.p1.w2.impl;

/**
 * Created by takoe on 20.02.17.
 */
public class ShellSort extends AbstractSort {

    @Override
    protected void executeSorting(Comparable[] a) {
        int h = 1;
        while (h < a.length/3) h = 3*h + 1;
        while (h >= 1) {
            hSort(a, h);
            h /= 3;
        }
    }

    private void hSort(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            for (int j = i-h; j >= 0 && less(a[j+h], a[j]); j--)
                swap(a, j, j+h);
    }

}
