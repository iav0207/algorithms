package ru.iav.std.algorithms.p1.w2.stack;

import ru.iav.std.algorithms.p1.w2.Stack;

/**
 * Created by takoe on 16.02.17.
 */
public class ResizableArrayStack<Item> implements Stack<Item> {

    private final int initialCapacity = 1;

    private Item[] s = initArray(initialCapacity);

    private int n = 0;

    @Override
    public void push(Item item) {
        if (needToEnlarge()) enlarge();
        s[n++] = item;
    }

    private boolean needToEnlarge() {
        return n == s.length - 1;
    }

    private void enlarge() {
        resize(s.length * 2);
    }

    @Override
    public Item pop() {
        if (isEmpty()) return null;
        Item itemToRemove = s[--n];
        s[n + 1] = null;
        if (needToCompress()) compress();
        return itemToRemove;
    }

    private boolean needToCompress() {
        return n > 1 && n <= s.length / 4;
    }

    private void compress() {
        resize(s.length / 2);
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int newCapacity) {
        Item[] s1 = initArray(newCapacity);
        int itemsToCopy = Math.min(s.length, s1.length);
        System.arraycopy(s, 0, s1, 0, itemsToCopy);
        s = s1;
    }

    @SuppressWarnings("unchecked")
    private Item[] initArray(int capacity) {
        return (Item[]) new Object[capacity];
    }

}
