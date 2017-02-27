package ru.iav.std.algorithms.p1.w4.pq;

/**
 * Created by takoe on 27.02.17.
 */
public class MinBinaryHeap<Key extends Comparable<Key>>
        extends BinaryHeap<Key>
        implements MinPQ<Key> {

    /**
     * {@inheritDoc}
     */
    public MinBinaryHeap() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public MinBinaryHeap(Key[] a) {
        super(a);
    }

    /**
     * Removes minimum key from the heap, rearranging the array
     * to keep it heap-ordered.
     * @return the minimum item currently in the heap.
     * @throws IllegalStateException if the heap is empty.
     */
    @Override
    public Key deleteMin() {
        return super.deleteRoot();
    }

    @Override
    protected boolean compare(Key a, Key b) {
        return greater(a, b);
    }

    private boolean greater(Key a, Key b) {
        return a.compareTo(b) > 0;
    }

}
