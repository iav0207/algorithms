package ru.iav.std.algorithms.p1.w3.sort;

import ru.iav.std.algorithms.p1.w2.Sort;
import ru.iav.std.algorithms.p1.w2.sort.SortTest;

/**
 * Created by takoe on 24.02.17.
 */
public class QuickWithInsertionSortTest extends SortTest {

    @Override
    protected Sort sort() {
        return new QuickWithInsertionSort();
    }

}