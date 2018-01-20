package ru.iav.std.algorithms.p2.w1.task;

import edu.princeton.cs.algs4.Digraph;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SAPTest {

    @Test(dataProviderClass = SAPTestData.class, dataProvider = "ancestors")
    public void testAncestor(Digraph g, int v, int w, int expected) {
        assertEquals(new SAP(g).ancestor(v, w), expected);
    }

    @Test(dataProviderClass = SAPTestData.class, dataProvider = "lengths")
    public void testLength(Digraph g, int v, int w, int expected) {
        assertEquals(new SAP(g).length(v, w), expected);
    }

}

