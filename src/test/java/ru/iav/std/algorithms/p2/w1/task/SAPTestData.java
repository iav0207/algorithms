package ru.iav.std.algorithms.p2.w1.task;

import org.testng.annotations.DataProvider;

import static ru.iav.std.algorithms.p2.w1.task.TestDigraphs.NINE;
import static ru.iav.std.algorithms.p2.w1.task.TestDigraphs.ONE;

public class SAPTestData {

    @DataProvider(name = "lengths")
    static Object[][] lengths() {
        return new Object[][] {
                {ONE, 3, 11, 4},
                {ONE, 9, 12, 3},
                {ONE, 7, 2, 4},
                {ONE, 1, 6, -1},
                {NINE, 1, 5, 2},
                {NINE, 7, 5, 4},
        };
    }

    @DataProvider(name = "ancestors")
    static Object[][] ancestors() {
        return new Object[][] {
                {ONE, 3, 11, 1},
                {ONE, 9, 12, 5},
                {ONE, 7, 2, 0},
                {ONE, 1, 6, -1},
                {NINE, 1, 5, 1}, // 1 or 4
                {NINE, 7, 5, 4},
        };
    }

}
