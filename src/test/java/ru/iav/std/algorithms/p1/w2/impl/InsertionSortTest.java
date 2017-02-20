package ru.iav.std.algorithms.p1.w2.impl;

import ru.iav.std.algorithms.p1.w2.Sort;

/**
 * Created by takoe on 20.02.17.
 */
public class InsertionSortTest extends SortTest {

    private Sort insertionSort = new InsertionSort();

    protected Sort sort() {
        return insertionSort;
    }

}