package ru.iav.std.algorithms.p1.w4.sort;

import ru.iav.std.algorithms.p1.w2.sort.AbstractSort;
import ru.iav.std.algorithms.p1.w4.pq.MinBinaryHeap;

/**
 * Created by takoe on 27.02.17.
 */
public class HeapSort extends AbstractSort {

    @Override
    protected void executeSorting(Comparable[] a) {
        MinBinaryHeap<?> heap = new MinBinaryHeap<>(a);
        for (int i = 0; i < a.length; i++)
            a[i] = heap.deleteMin();
    }

}
