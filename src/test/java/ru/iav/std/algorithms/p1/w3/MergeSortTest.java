package ru.iav.std.algorithms.p1.w3;

import ru.iav.std.algorithms.p1.w2.Sort;
import ru.iav.std.algorithms.p1.w2.impl.SortTest;

/**
 * Created by takoe on 20.02.17.
 */
public class MergeSortTest extends SortTest {

    private Sort mergeSort = new MergeSort();

    protected Sort sort() {
        return mergeSort;
    }

}