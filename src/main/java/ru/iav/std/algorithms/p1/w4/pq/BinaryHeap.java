package ru.iav.std.algorithms.p1.w4.pq;

import java.util.Arrays;

/**
 * An implementation of max-priority queue data structure over a resizable array.
 */
public class BinaryHeap<Key extends Comparable<Key>> implements MaxPQ<Key> {

    private static final int initialCapacity = 2;

    private Key[] a;

    private int n;

    /**
     * Create a resizable array binary heap.
     */
    public BinaryHeap() {
        this.a = initArray(initialCapacity);
    }

    /**
     * Create a binary heap from an array of key elements.
     * The heap then uses a defensive copy of a given array,
     * which is, by the way, resizable.
     *
     * @param a array of comparable keys.
     */
    public BinaryHeap(Key[] a) {
        this.a = Arrays.copyOf(a, a.length);
    }

    /**
     * Store a new key in the heap, keeping the array heap-ordered.
     * @param key an element to be added.
     */
    @Override
    public void insert(Key key) throws IllegalStateException {
        extendIfNeedTo();
        a[++n] = key;
        swim(n);
    }

    /**
     * Removes maximum key from the heap, rearranging the array
     * to keep it heap-ordered.
     * @return the maximum item currently in the heap.
     * @throws IllegalStateException if the heap is empty.
     */
    @Override
    public Key deleteMax() throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("The heap is empty. Nothing to delete.");

        Key max = a[1];
        swap(1, n--);
        sink(1);

        a[n + 1] = null;
        shrinkIfNeedTo();
        return max;
    }

    @Override
    public boolean isEmpty() {
        return n < 1;
    }

    @Override
    public int size() {
        return n;
    }

    private void swim(int i) {
        while (i > 1 && less(i/2, i)) {
            swap(i/2, i);
            i /= 2;
        }
    }

    private void sink(int i) {
        while (i <= n / 2) {
            int j = 2 * i;
            if (j < n && less(j, j+1))
                j++;
            if (!less(i, j))
                break;
            swap(i, j);
            i = j;
        }
    }

    private void swap(int i, int j) {
        Key swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private boolean less(int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private void extendIfNeedTo() {
        if (n + 1 >= a.length)
            resize(a.length * 2);
    }

    private void shrinkIfNeedTo() {
        if (n > 1 && n < a.length / 4)
            resize(a.length / 2);
    }

    private void resize(int newCapacity) {
        a = Arrays.copyOf(a, newCapacity + 1);
    }

    @SuppressWarnings("unchecked")
    private Key[] initArray(int capacity) {
        return (Key[]) new Comparable[capacity];
    }

}
