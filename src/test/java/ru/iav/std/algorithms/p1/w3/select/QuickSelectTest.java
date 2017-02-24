package ru.iav.std.algorithms.p1.w3.select;

import edu.princeton.cs.algs4.StdRandom;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 24.02.17.
 */
public class QuickSelectTest {

    private static final int items = 10_000;

    private static final int k = 50;

    @Test(dataProvider = "select")
    public void shouldFindKthLargestElement(Integer[] integers, int expected) {
        assertEquals(new QuickSelect().select(integers, k), expected);
    }

    @DataProvider(name = "select")
    public static Object[][] random() {
        return new Object[][]{
                {createDistinctIntegersArray(), k},
                {createSortedIntegersArray(),   k}
        };
    }

    private static Integer[] createDistinctIntegersArray() {
        Integer[] array = IntStream.range(0, items)
                .boxed()
                .collect(Collectors.toList())
                .toArray(new Integer[items]);
        StdRandom.shuffle(array);
        return array;
    }

    private static Integer[] createSortedIntegersArray() {
        return IntStream.range(0, items)
                .boxed()
                .collect(Collectors.toList())
                .toArray(new Integer[items]);
    }

}