package ru.iav.std.algorithms.p1.w2.impl;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.p1.w2.Stack;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by takoe on 16.02.17.
 */
public class SingleLinkedStackTest {

    @Test
    public void shouldStackBeEmptyIfJustCreated() throws Exception {
        assertTrue(new SingleLinkedStack<>().isEmpty());
    }

    @Test
    public void shouldBeEmptyIfAllItemsPopped() throws Exception {
        Stack<Integer> stack = new SingleLinkedStack<>();
        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.push(5);
        stack.push(0);
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void shouldStaySilentAndReturnNullIfEmptyStackPopped() throws Exception {
        assertNull(new SingleLinkedStack<>().pop());
    }

    @Test
    public void shouldBeLIFO() throws Exception {
        Stack<Integer> stack = new SingleLinkedStack<>();
        int[] array = IntStream.range(0, 100).toArray();
        Arrays.stream(array).forEach(stack::push);
        for (int i = array.length - 1; i >= 0; i--) {
            assertEquals(stack.pop(), (Integer) array[i]);
        }
        assertTrue(stack.isEmpty());
    }

}