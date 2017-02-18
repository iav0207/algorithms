package ru.iav.std.algorithms.p1.w2.task;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static edu.princeton.cs.algs4.StdStats.mean;
import static edu.princeton.cs.algs4.StdStats.stddev;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static ru.iav.std.algorithms.util.PerformanceTester.measureAverageExecutionTime;

/**
 * Created by takoe on 18.02.17.
 */
public class RandomizedQueueTest {

    private Random random = ThreadLocalRandom.current();

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfClientAddsNullItem() {
        new RandomizedQueue<Integer>().enqueue(null);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionWhenSamplingFromAnEmptyQueue() {
        new RandomizedQueue<>().sample();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionWhenCallingDequeueFromAnEmptyQueue() {
        new RandomizedQueue<>().dequeue();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void shouldIteratorThrowUnsupportedOpExceptionIfCalledRemove() {
        new RandomizedQueue<>().iterator().remove();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldIteratorThrowNoSuchElementExceptionIfCalledNextWhenNoItemsLeft() {
        RandomizedQueue<Integer> queue = generateQueue();
        Iterator<Integer> iterator = queue.iterator();
        try {
            while (iterator.hasNext()) {
                iterator.next();
            }
        } catch (NoSuchElementException ex) {
            // test should fail if exception was caught here
            assertTrue(false);
        }
        iterator.next();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldNotReturnNull() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 20; i++) {
            assertNotNull(random.nextBoolean() ? queue.dequeue() : queue.sample(), String.format("\ni = %d\n", i));
        }
    }

    @Test
    public void testIsEmpty() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
        queue.enqueue(0);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testSize() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        assertEquals(queue.size(), 0);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(18);
        queue.dequeue();
        assertEquals(queue.size(), 2);

        assertEquals(generateQueue().size(), 100);
    }

    @Test
    public void shouldReturnItemsInRandomOrder() {
        generateQueue().forEach(System.out::println);
    }

    @Test
    public void testOrderOfGrowth() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int trials = 1000;
        double[] measures = new double[trials];
        for (int i = 0; i < trials; i++) {
            measures[i] = measureAverageExecutionTime(() -> {
                for (int k = 0; k < 10000; k++) {
                    queue.enqueue(0);
                    if (k % 2 == 0) queue.dequeue();
                }
                return 0;
            });
        }
        System.out.println("Mean time is: " + mean(measures));
        System.out.println("Standard deviation is: " + stddev(measures));
    }
    
    private RandomizedQueue<Integer> generateQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        IntStream.range(-50, 50).forEach(queue::enqueue);
        return queue;
    }

    private RandomizedQueue<Integer> generateQueue(int size) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        IntStream.range(0, size).forEach(queue::enqueue);
        return queue;
    }

}