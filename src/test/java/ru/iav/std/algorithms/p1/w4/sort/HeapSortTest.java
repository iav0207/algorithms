package ru.iav.std.algorithms.p1.w4.sort;

import ru.iav.std.algorithms.p1.w2.Sort;
import ru.iav.std.algorithms.p1.w2.sort.SortTest;

/**
 * Created by takoe on 27.02.17.
 */
public class HeapSortTest extends SortTest {

    @Override
    protected Sort sort() {
        return new HeapSort();
    }

}