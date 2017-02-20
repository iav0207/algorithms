package ru.iav.std.algorithms.p1.w2.impl;

import ru.iav.std.algorithms.p1.w2.Sort;

/**
 * Created by takoe on 20.02.17.
 */
public class SelectionSortTest extends SortTest {

    private Sort selectionSort = new SelectionSort();

    protected Sort sort() {
        return selectionSort;
    }

}