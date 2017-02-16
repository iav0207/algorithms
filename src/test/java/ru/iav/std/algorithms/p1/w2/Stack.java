package ru.iav.std.algorithms.p1.w2;

/**
 * Created by takoe on 16.02.17.
 */
public interface Stack<Item> {

    /**
     * Insert a new item onto stack.
     * @param item an item to be inserted.
     */
    void push(Item item);

    /**
     * Remove and return the item most recently added.
     * @return The most recently added item.
     */
    Item pop();

    /**
     * Is the stack empty?
     * @return <tt>true</tt>, if the stack contains no items.
     */
    boolean isEmpty();

}
