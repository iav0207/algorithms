package ru.iav.std.algorithms.p1.w3.sort;

import ru.iav.std.algorithms.p1.w2.Sort;
import ru.iav.std.algorithms.p1.w2.sort.SortTest;
import ru.iav.std.algorithms.p1.w3.sort.BottomUpMergeSort;

/**
 * Created by takoe on 20.02.17.
 */
public class BottomUpMergeSortTest extends SortTest {

    private Sort bottomUpMergeSort = new BottomUpMergeSort();

    protected Sort sort() {
        return bottomUpMergeSort;
    }

}