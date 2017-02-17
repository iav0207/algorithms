package ru.iav.std.algorithms.p1.w2.impl;

import ru.iav.std.algorithms.p1.w2.Queue;

/**
 * Created by takoe on 17.02.17.
 */
public class ResizableArrayQueue<Item> implements Queue<Item> {

    private final int initialCapacity = 1;

    private Item[] s = initArray(initialCapacity);

    private int head = 0;
    private int tail = 0;

    @Override
    public void enqueue(Item item) {
        if (needToEnlarge()) enlarge();
        s[tail] = item;
        tail = ++tail % s.length;
    }

    private boolean needToEnlarge() {
        return size() == s.length - 1;
    }

    private void enlarge() {
        resize(s.length * 2);
    }

    @Override
    public Item dequeue() {
        if (needToCompress()) compress();
        Item itemToRemove = s[head];
        s[head] = null;
        head = ++head % s.length;
        return itemToRemove;
    }

    private boolean needToCompress() {
        int size = size();
        return size > 1 && size <= (s.length / 4);
    }

    private void compress() {
        resize(s.length / 2);
    }

    private int size() {
        return (tail >= head) ? (tail - head) : (s.length + tail - head);
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    private void resize(int newCapacity) {
        Item[] s1 = initArray(newCapacity);
        int size = size();
        for (int i = 0; i < size; i++) {
            s1[i] = s[(i + head) % s.length];
        }
        s = s1;
        head = 0;
        tail = size;
    }

    @SuppressWarnings("unchecked")
    private Item[] initArray(int capacity) {
        return (Item[]) new Object[capacity];
    }

}
