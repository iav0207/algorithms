package ru.iav.std.algorithms.p1.w2.queue;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.p1.w2.Queue;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by takoe on 17.02.17.
 */
public abstract class QueueTest {

    @Test
    public void shouldBeEmptyIfJustCreated() throws Exception {
        assertTrue(newQueue().isEmpty());
    }

    @Test
    public void shouldBeEmptyIfHasBeenFullyDequeued() throws Exception {
        Queue<Integer> queue = newQueue();
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        queue.enqueue(5);
        queue.enqueue(0);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void shouldStaySilentAndReturnNullIfEmptyStackPopped() throws Exception {
        assertNull(newQueue().dequeue());
    }

    @Test
    public void shouldBeFIFO() throws Exception {
        Queue<Integer> queue = newQueue();
        int[] array = IntStream.range(0, 100).toArray();
        Arrays.stream(array).forEach(queue::enqueue);
        Arrays.stream(array).forEach(i -> assertEquals(queue.dequeue(), (Integer) i));
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPerformance() throws Exception {
        // TODO implement
    }

    abstract  <Item> Queue<Item> newQueue();

}
