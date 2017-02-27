package ru.iav.std.algorithms.p1.w4.pq;

import static org.testng.Assert.assertTrue;

/**
 * Created by takoe on 27.02.17.
 */
public class MaxBinaryHeapTest extends BinaryHeapTest {

    @Override
    BinaryHeap<Integer> initHeap() {
        return new MaxBinaryHeap<>();
    }

    @Override
    void runDeletions(BinaryHeap<Integer> heap) {
        MaxBinaryHeap<Integer> maxHeap = (MaxBinaryHeap<Integer>) heap;
        Integer last = maxHeap.deleteMax();
        int size = maxHeap.size();
        for (int i = 0; i < size; i++) {
            Integer current = maxHeap.deleteMax();
            assertTrue(last.compareTo(current) >= 0);
            last = current;
        }
    }

}