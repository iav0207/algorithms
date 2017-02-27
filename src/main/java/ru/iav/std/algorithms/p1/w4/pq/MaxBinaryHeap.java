package ru.iav.std.algorithms.p1.w4.pq;

/**
 * Created by takoe on 27.02.17.
 */
public class MaxBinaryHeap<Key extends Comparable<Key>>
        extends BinaryHeap<Key>
        implements MaxPQ<Key> {

    /**
     * Removes maximum key from the heap, rearranging the array
     * to keep it heap-ordered.
     * @return the maximum item currently in the heap.
     * @throws IllegalStateException if the heap is empty.
     */
    @Override
    public Key deleteMax() throws IllegalStateException {
        return super.deleteRoot();
    }

    @Override
    protected boolean compare(Key a, Key b) {
        return less(a, b);
    }

    private boolean less(Key a, Key b) {
        return a.compareTo(b) < 0;
    }

}
