package ru.iav.std.algorithms.p1.w4.pq;

import static org.testng.Assert.*;

/**
 * Created by takoe on 27.02.17.
 */
public class MinBinaryHeapTest extends BinaryHeapTest {

    @Override
    BinaryHeap<Integer> initHeap() {
        return new MinBinaryHeap<>();
    }

    @Override
    void runDeletions(BinaryHeap<Integer> heap) {
        MinBinaryHeap<Integer> maxHeap = (MinBinaryHeap<Integer>) heap;
        Integer last = maxHeap.deleteMin();
        int size = maxHeap.size();
        for (int i = 0; i < size; i++) {
            Integer current = maxHeap.deleteMin();
            assertTrue(last.compareTo(current) <= 0);
            last = current;
        }
    }

}