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

    protected abstract Sort sort();

    @Test(dataProvider = "sortData")
    public void shouldSort(List<Integer> list, Object ignore) throws Exception {
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

    @DataProvider(name = "sortData")
    public static Object[][] sortData() {
        return new Object[][] {
                {
                        IntStream.generate(() -> random.nextInt())
                                .limit(1000)
                                .boxed()
                                .collect(Collectors.toList()),
                        null
                }
        };
    }

}
