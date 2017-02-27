package ru.iav.std.algorithms.p1.w4.pq;

import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

/**
 * Created by takoe on 27.02.17.
 */
public class BinaryHeapTest {

    private Random random = ThreadLocalRandom.current();

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfCapacityIsZero() {
        new BinaryHeap<Integer>(0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfCapacityIsNegative() {
        new BinaryHeap<Integer>(-1);
    }

    @Test
    public void shouldConstructSilentlyIfCapacityIsPositive() {
        new BinaryHeap<Integer>(1);
        new BinaryHeap<Integer>(1100);
        new BinaryHeap<Integer>(11000000);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void shouldThrowIllegalStateExceptionOnAttemptToDeleteFromAnEmptyHeap() {
        new BinaryHeap<Integer>(10).deleteMax();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void shouldThrowIllegalStateExceptionOnAttemptToInsertIntoAFilledHeap() {
        MaxPQ<Integer> heap = new BinaryHeap<>(10);
        try {
            random.ints(10).boxed().forEach(heap::insert);
        } catch (IllegalStateException ex) {
            fail();
        }
        heap.insert(0);
    }

    @Test
    public void shouldReturnDescendingKeySequence() {
        int capacity = 100;
        MaxPQ<Integer> heap = new BinaryHeap<>(capacity);
        assertTrue(heap.isEmpty());

        random.ints().limit(capacity).boxed().forEach(heap::insert);
        assertFalse(heap.isEmpty());
        assertEquals(heap.size(), capacity);

        Integer last = heap.deleteMax();
        for (int i = 0; i < capacity - 1; i++) {
            Integer current = heap.deleteMax();
            assertTrue(last.compareTo(current) >= 0);
            last = current;
        }

        assertEquals(heap.size(), 0);
        assertTrue(heap.isEmpty());
    }

}