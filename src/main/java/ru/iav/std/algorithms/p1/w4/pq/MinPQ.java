package ru.iav.std.algorithms.p1.w4.pq;

/**
 * Created by takoe on 27.02.17.
 */
public interface MinPQ<Key extends Comparable<Key>> extends PriorityQueue<Key> {

    Key deleteMin();

}
