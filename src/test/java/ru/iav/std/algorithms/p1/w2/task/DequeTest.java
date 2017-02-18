package ru.iav.std.algorithms.p1.w2.task;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.util.PerformanceTester;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by takoe on 18.02.17.
 */
public class DequeTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfClientAddsNullItemFirst() {
        new Deque<Integer>().addFirst(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowMpeIfClientAddsNullItemLast() {
        new Deque<Integer>().addLast(null);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionWhenRemovingFirstFromEmptyDeque() {
        new Deque<Integer>().removeFirst();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionWhenRemovingLastFromEmptyDeque() {
        new Deque<Integer>().removeLast();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void shouldIteratorThrowUnsupportedOpExceptionIfCalledRemove() {
        new Deque<Integer>().iterator().remove();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldIteratorThrowNoSuchElementExceptionIfCalledNextWhenNoItemsLeft() {
        Deque<Integer> deque = generateDeque();
        Iterator<Integer> iterator = deque.iterator();
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

    @Test
    public void testAddFirst() {
        Deque<Integer> deque = generateDeque();
        final Integer newFirst = -999;
        deque.addFirst(newFirst);
        assertNotEquals(deque.removeLast(), newFirst);
        assertEquals(deque.removeFirst(), newFirst);

        deque = new Deque<>();
        deque.addFirst(newFirst);
        assertEquals(deque.removeLast(), newFirst);
    }

    @Test
    public void testAddLast() {
        Deque<Integer> deque = generateDeque();
        final Integer newLast = -999;
        deque.addLast(newLast);
        assertNotEquals(deque.removeFirst(), newLast);
        assertEquals(deque.removeLast(), newLast);

        deque = new Deque<>();
        deque.addFirst(newLast);
        assertEquals(deque.removeFirst(), newLast);
    }

    @Test
    public void testIsEmpty() {
        Deque<Integer> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        deque.removeFirst();
        assertTrue(deque.isEmpty());
        deque.addLast(0);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(generateDeque().size(), 100);

        Deque<Integer> deque = new Deque<>();
        assertEquals(deque.size(), 0);
        deque.addLast(1);
        deque.addFirst(1);
        deque.addLast(18);
        deque.removeFirst();
        assertEquals(deque.size(), 2);

        deque.addFirst(1);
        deque.addLast(18);
        deque.removeLast();
        assertEquals(deque.size(), 3);
    }

    private Deque<Integer> generateDeque() {
        Deque<Integer> deque = new Deque<>();
        IntStream.range(-50, 50).forEach(deque::addFirst);
        assertEquals(IntStream.range(-50, 50).count(), 100);
        return deque;
    }

    @Test(enabled = false)  // unsuccessful
    public void shouldTakeConstantTimeForEachOperation() {
        PerformanceTester tester = new PerformanceTester(0.15);
        Deque<Integer> small = generateDeque(100);
        Deque<Integer> large = generateDeque(100_000);
        assertTrue(tester.isEqualExecutionTime(addFirst(small), addFirst(large)));
        assertTrue(tester.isEqualExecutionTime(addLast(small), addLast(large)));
        assertTrue(tester.isEqualExecutionTime(removeFirst(small), removeFirst(large)));
        assertTrue(tester.isEqualExecutionTime(removeLast(small), removeLast(large)));
    }

    private Supplier<?> addFirst(Deque<Integer> deque) {
        return () -> {
            for (int i = 0; i < 100_000; i++) deque.addFirst(0);
            return 0;
        };
    }

    private Supplier<?> addLast(Deque<Integer> deque) {
        return () -> {
            for (int i = 0; i < 100_000; i++) deque.addLast(0);
            return 0;
        };
    }

    private Supplier<?> removeFirst(Deque<Integer> deque) {
        return () -> {
            for (int i = 0; i < 100_000; i++) deque.removeFirst();
            return 0;
        };
    }

    private Supplier<?> removeLast(Deque<Integer> deque) {
        return () -> {
            for (int i = 0; i < 100_000; i++) deque.removeLast();
            return 0;
        };
    }

    private Deque<Integer> generateDeque(int size) {
        Deque<Integer> deque = new Deque<>();
        IntStream.range(0, size).forEach(deque::addFirst);
        return deque;
    }

}