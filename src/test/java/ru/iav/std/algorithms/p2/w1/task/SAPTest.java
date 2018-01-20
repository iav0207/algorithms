package ru.iav.std.algorithms.p2.w1.task;

import edu.princeton.cs.algs4.Digraph;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static ru.iav.std.algorithms.p2.w1.task.TestDigraphs.FIVE;
import static ru.iav.std.algorithms.p2.w1.task.TestDigraphs.NINE;
import static ru.iav.std.algorithms.p2.w1.task.TestDigraphs.ONE;
import static ru.iav.std.algorithms.p2.w1.task.TestDigraphs.THREE;

public class SAPTest {

    @Test(dataProvider = "ancestors")
    public void testAncestor(Digraph g, int v, int w, int expected) {
        assertEquals(new SAP(g).ancestor(v, w), expected);
    }

    @Test(dataProvider = "lengths")
    public void testLength(Digraph g, int v, int w, int expected) {
        assertEquals(new SAP(g).length(v, w), expected);
    }

    @DataProvider(name = "lengths")
    static Object[][] lengths() {
        return new Object[][] {
                {ONE, 3, 11, 4},
                {ONE, 9, 12, 3},
                {ONE, 7, 2, 4},
                {ONE, 1, 6, -1},
                {NINE, 1, 5, 2},
                {NINE, 7, 5, 4},
                {THREE, 13, 9, 5},
                {FIVE, 7, 21, 4},
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
                {THREE, 13, 9, 11},
                {FIVE, 7, 21, 9},
        };
    }

}

