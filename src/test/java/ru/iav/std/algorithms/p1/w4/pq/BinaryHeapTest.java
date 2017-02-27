package ru.iav.std.algorithms.p1.w4.pq;

import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfConstructorArgumentIsNull() {
        initHeap(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfConstructorArgumentContainsNull() {
        Integer[] array = createRandomIntegersArray(10);
        array[3] = null;
        initHeap(array);
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

    @Test
    public void shouldReturnDescendingKeySequenceConstructedWithInputArray() {
        int n = random.nextInt(100) + 1;
        BinaryHeap<Integer> heap = initHeap(createRandomIntegersArray(n));
        assertFalse(heap.isEmpty());
        assertEquals(heap.size(), n);

        runDeletions(heap);

        assertEquals(heap.size(), 0);
        assertTrue(heap.isEmpty());
    }

    private Integer[] createRandomIntegersArray(int size) {
        return random.ints(size)
                .boxed()
                .collect(Collectors.toList())
                .toArray(new Integer[size]);
    }

    abstract BinaryHeap<Integer> initHeap();

    abstract BinaryHeap<Integer> initHeap(Integer[] array);

    abstract void runDeletions(BinaryHeap<Integer> heap);

}