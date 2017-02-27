package ru.iav.std.algorithms.p1.w4.pq;

import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by takoe on 27.02.17.
 */
public class BinaryHeapTest {

    private Random random = ThreadLocalRandom.current();

    @Test(expectedExceptions = IllegalStateException.class)
    public void shouldThrowIllegalStateExceptionOnAttemptToDeleteFromAnEmptyHeap() {
        new BinaryHeap<Integer>().deleteMax();
    }

    @Test(invocationCount = 100)
    public void shouldReturnDescendingKeySequence() {
        int n = random.nextInt(100) + 1;
        MaxPQ<Integer> heap = new BinaryHeap<>();
        assertTrue(heap.isEmpty());

        random.ints().limit(n).boxed().forEach(heap::insert);
        assertFalse(heap.isEmpty());
        assertEquals(heap.size(), n);

        Integer last = heap.deleteMax();
        for (int i = 0; i < n - 1; i++) {
            Integer current = heap.deleteMax();
            assertTrue(last.compareTo(current) >= 0);
            last = current;
        }

        assertEquals(heap.size(), 0);
        assertTrue(heap.isEmpty());
    }

}