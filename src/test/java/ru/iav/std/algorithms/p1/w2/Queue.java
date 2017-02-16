package ru.iav.std.algorithms.p1.w2;

/**
 * Created by takoe on 16.02.17.
 */
public interface Queue<Item> {

    /**
     * Insert a new item onto queue.
     * @param item The item to be inserted.
     */
    void enqueue(Item item);

    /**
     * Remove and return the item least recently added.
     * @return The least recently added item.
     */
    Item dequeue();

    /**
     * Is the queue empty?
     * @return <tt>true</tt>, if the queue contains no items.
     */
    boolean isEmpty();

}
