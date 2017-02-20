package ru.iav.std.algorithms.p1.w2.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.iav.std.algorithms.p1.w2.Sort;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.testng.Assert.assertTrue;

/**
 * Created by takoe on 20.02.17.
 */
public abstract class SortTest {

    private static Random random = ThreadLocalRandom.current();

    private static final int invocations = 1;

    private static final int items = 10_000;

    protected abstract Sort sort();

    @Test(dataProvider = "random", invocationCount = invocations)
    public void shouldSortRandomInput(List<Comparable<?>> list, Object ignore) throws Exception {
        assertSorted(list);
    }

    @Test(dataProvider = "sorted", invocationCount = invocations)
    public void shouldSortSortedInput(List<Comparable<?>> list, Object ignore) throws Exception {
        assertSorted(list);
    }

    private void assertSorted(List<Comparable<?>> list) {
        Comparable[] a = list.toArray(new Comparable[list.size()]);
        sort().sort(a);
        assertTrue(isSorted(a));
    }

    private boolean isSorted(Comparable[] a) {
        if (a == null) return false;
        if (a.length < 2) return true;
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i-1]) < 0) return false;
        }
        return true;
    }

    @DataProvider(name = "random")
    public static Object[][] random() {
        return new Object[][]{
                {
                        random.ints(items)
                                .boxed()
                                .collect(Collectors.toList()),
                        null
                }
        };
    }

    @DataProvider(name = "sorted")
    public static Object[][] sorted() {
        return new Object[][] {
                {
                        IntStream.range(0, items)
                                .boxed()
                                .collect(Collectors.toList()),
                        null
                }
        };
    }

}
