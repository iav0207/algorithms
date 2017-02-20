package ru.iav.std.algorithms.p1.w3;

import ru.iav.std.algorithms.p1.w2.Sort;
import ru.iav.std.algorithms.p1.w2.impl.SortTest;

/**
 * Created by takoe on 20.02.17.
 */
public class BottomUpMergeSortTest extends SortTest {

    private Sort bottomUpMergeSort = new BottomUpMergeSort();

    protected Sort sort() {
        return bottomUpMergeSort;
    }

}