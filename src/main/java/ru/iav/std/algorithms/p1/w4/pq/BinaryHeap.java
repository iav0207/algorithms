package ru.iav.std.algorithms.p1.w4.pq;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * An implementation of priority queue data structure over a resizable array.
 */
abstract class BinaryHeap<Key extends Comparable<Key>> implements PriorityQueue<Key> {

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
     * @throws NullPointerException if argument is null or contains nulls.
     */
    public BinaryHeap(Key[] a) throws NullPointerException {
        validate(a);
        this.a = initArray(a.length + 1);
        Stream.of(a).forEach(this::insert);
    }

    private void validate(Key[] a) {
        Objects.requireNonNull(a, "Input argument must be non-null.");
        Stream.of(a).forEach(key -> Objects.requireNonNull(key, "Input array must not contain nulls."));
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
     * Removes root key from the heap, rearranging the array
     * to keep it heap-ordered.
     * @return the root item of the heap.
     * @throws IllegalStateException if the heap is empty.
     */
    protected Key deleteRoot() throws IllegalStateException {
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
        while (i > 1 && compare(i/2, i)) {
            swap(i/2, i);
            i /= 2;
        }
    }

    private void sink(int i) {
        while (i <= n / 2) {
            int j = 2 * i;
            if (j < n && compare(j, j+1))
                j++;
            if (!compare(i, j))
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

    private boolean compare(int i, int j) {
        return compare(a[i], a[j]);
    }

    protected abstract boolean compare(Key a, Key b);

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
