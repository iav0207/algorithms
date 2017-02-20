package ru.iav.std.algorithms.p1.w2.impl;

import ru.iav.std.algorithms.p1.w2.Sort;

/**
 * Created by takoe on 20.02.17.
 */
public class ShellSortTest extends SortTest {

    private Sort shellSort = new ShellSort();

    protected Sort sort() {
        return shellSort;
    }

}