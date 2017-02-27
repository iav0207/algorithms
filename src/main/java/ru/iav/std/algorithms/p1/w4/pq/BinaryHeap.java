package ru.iav.std.algorithms.p1.w4.pq;

/**
 * Created by takoe on 27.02.17.
 */
public class BinaryHeap<Key extends Comparable<Key>> implements MaxPQ<Key> {

    private final Key[] a;

    private int n;

    /**
     *
     * @param capacity
     */
    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be positive.");
        this.a = (Key[]) new Comparable[capacity + 1];
    }

    /**
     *
     * @param key
     */
    @Override
    public void insert(Key key) {
        if (n + 1 == a.length) throw new IllegalStateException("Capacity reached. The new element is not inserted.");
        a[++n] = key;
        swim(n);
    }

    @Override
    public Key deleteMax() {
        if (isEmpty()) throw new IllegalStateException("The heap is empty. Nothing to delete.");
        Key max = a[1];
        swap(1, n--);
        sink(1);
        a[n + 1] = null;
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
        while (i <= n/2) {
            int left = 2*i;
            int right = 2*i + 1;
            if (exists(right)) {
                int maxChild = less(left, right) ? right : left;
                if (less(i, maxChild)) {
                    swap(i, maxChild);
                    i = maxChild;
                } else {
                    break;
                }
            } else {
                if (less(i, left))      swap(i, left);
                i = left;
            }
        }
    }

    private boolean exists(int keyIndex) {
        return keyIndex > 0 && keyIndex <= n;
    }

    private void swap(int i, int j) {
        Key swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private boolean less(int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

}
