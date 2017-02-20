package ru.iav.std.algorithms.p1.w2.sort;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.p1.w2.Sort;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by takoe on 20.02.17.
 */
public abstract class SortTest {

    private static final int invocations = 1;

    @Test(dataProviderClass = SortTestData.class, dataProvider = "random", invocationCount = invocations)
    public void shouldSortRandomInput(List<Comparable<?>> list, Object ignore) throws Exception {
        assertSorted(list);
    }

    @Test(dataProviderClass = SortTestData.class, dataProvider = "sorted", invocationCount = invocations)
    public void shouldSortSortedInput(List<Comparable<?>> list, Object ignore) throws Exception {
        assertSorted(list);
    }

    private void assertSorted(List<Comparable<?>> list) {
        Comparable[] a = list.toArray(new Comparable[list.size()]);
        sort().sort(a);
        assertTrue(isSorted(a));
    }

    protected abstract Sort sort();

    private boolean isSorted(Comparable[] a) {
        if (a == null) return false;
        if (a.length < 2) return true;
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i-1]) < 0) return false;
        }
        return true;
    }

}
