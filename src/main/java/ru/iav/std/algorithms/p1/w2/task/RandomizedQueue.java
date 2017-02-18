package ru.iav.std.algorithms.p1.w2.task;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.StdRandom.shuffle;
import static edu.princeton.cs.algs4.StdRandom.uniform;

/**
 * A randomized queue is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random
 * from items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int initialCapacity = 1;

    private Item[] s = initArray(initialCapacity);

    private int tail = 0;

    /**
     * Is the queue empty?
     * @return
     */
    public boolean isEmpty() {
        return tail == 0;
    }

    /**
     * Return the number of items on the queue
     */
    public int size() {
        return tail;
    }

    /**
     * Add the item
     * @param item an item to add. Must be non-null.
     */
    public void enqueue(Item item) {
        requireNonNull(item);
        if (needToExtend()) extend();
        s[tail++] = item;
    }

    private boolean needToExtend() {
        return tail == s.length - 1;
    }

    private void extend() {
        resize(s.length * 2);
    }

    /**
     * Remove and return a random item.
     * @return A randomly selected item to be removed.
     */
    public Item dequeue() {
        verifyNotEmpty();
        int i = randomIndex();
        Item itemToDequeue = s[i];
        s[i] = s[tail];
        s[tail] = null;
        tail--;
        if(needToCompress()) compress();
        return itemToDequeue;
    }

    private boolean needToCompress() {
        return tail > 1 && tail <= s.length / 4;
    }

    private void compress() {
        resize(s.length / 2);
    }

    private void resize(int newCapacity) {
        Item[] s1 = initArray(newCapacity);
        int itemsToCopy = Math.min(s.length, s1.length);
        System.arraycopy(s, 0, s1, 0, itemsToCopy);
        s = s1;
    }

    /**
     * Return (but do not remove) a random item
     * @return Random item without its removal.
     */
    public Item sample() {
        verifyNotEmpty();
        return s[randomIndex()];
    }

    private int randomIndex() {
        return uniform(size());
    }

    private void requireNonNull(Item item) {
        if (item == null) throw new NullPointerException("An item must be non-null");
    }

    private void verifyNotEmpty() {
        if (isEmpty()) throw new NoSuchElementException("There are no elements to return.");
    }

    @SuppressWarnings("unchecked")
    private Item[] initArray(int capacity) {
        return (Item[]) new Object[capacity];
    }

    /**
     * Return an independent iterator over items in random order
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {

        private final Item[] shuffle;
        private int i = 0;

        private RandomQueueIterator() {
            shuffle = initArray(size());
            System.arraycopy(s, 0, shuffle, 0, shuffle.length);
            shuffle(shuffle);
        }

        @Override
        public boolean hasNext() {
            return i < shuffle.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return shuffle[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit testing (optional)
     * @param args
     */
    public static void main(String[] args) {}

}
