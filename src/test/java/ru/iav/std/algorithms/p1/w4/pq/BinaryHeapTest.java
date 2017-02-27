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
public abstract class BinaryHeapTest {

    private Random random = ThreadLocalRandom.current();

    @Test(expectedExceptions = IllegalStateException.class)
    public void shouldThrowIllegalStateExceptionOnAttemptToDeleteFromAnEmptyHeap() {
        initHeap().deleteRoot();
    }

    @Test(invocationCount = 100)
    public void shouldReturnDescendingKeySequence() {
        int n = random.nextInt(100) + 1;
        BinaryHeap<Integer> heap = initHeap();
        assertTrue(heap.isEmpty());

        random.ints().limit(n).boxed().forEach(heap::insert);
        assertFalse(heap.isEmpty());
        assertEquals(heap.size(), n);

        runDeletions(heap);

        assertEquals(heap.size(), 0);
        assertTrue(heap.isEmpty());
    }

    abstract BinaryHeap<Integer> initHeap();

    abstract void runDeletions(BinaryHeap<Integer> heap);

}