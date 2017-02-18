package ru.iav.std.algorithms.p1.w2.task;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by takoe on 18.02.17.
 */
public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Node next, prev;
        Item item;
        Node(Item item) {
            this.item = item;
        }
    }

    private Node first, last;

    /**
     * Construct an empty deque
     */
    public Deque() {}

    /**
     * Is the deque empty?
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items on the deque
     * @return
     */
    public int size() {
        if (isEmpty()) return 0;
        int size = 1;
        for (Node current = first; current.next != null; current = current.next) {
            size++;
        }
        return size;
    }

    /**
     * Add the item to the front
     * @param item an item to be added.
     */
    public void addFirst(Item item) {
        requireNonNull(item);
        if (isEmpty()) {
            first = last = new Node(item);
            return;
        }
        link(new Node(item), first);
        first = first.prev;
    }

    /**
     * Add the item to the end
     * @param item an item to be added.
     */
    public void addLast(Item item) {
        requireNonNull(item);
        if (isEmpty()) {
            first = last = new Node(item);
            return;
        }
        link(last, new Node(item));
        last = last.next;
    }

    /**
     * Connect the two nodes with each other.
     * @param prev first node.
     * @param next second node.
     */
    private void link(Node prev, Node next) {
        prev.next = next;
        next.prev = prev;
    }

    private void requireNonNull(Item item) {
        if (item == null) throw new NullPointerException("An item must be non-null");
    }

    /**
     * Remove and return the item from the front
     * @return An item removed from the deque.
     */
    public Item removeFirst() {
        verifyRemovalAllowed();
        Item itemToRemove = first.item;
        if (last.prev == first) last.prev = null;
        first = first.next;
        if (first == null) last = null;
        return itemToRemove;
    }

    /**
     * Remove and return the item from the end
     * @return An item removed from the deque.
     */
    public Item removeLast() {
        verifyRemovalAllowed();
        Item itemToRemove = last.item;
        if (first.next == last) first.next = null;
        last = last.prev;
        if (last == null) first = null;
        return itemToRemove;
    }

    private void verifyRemovalAllowed() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty! Nothing to remove.");
    }

    /**
     * Return an iterator over items in order from front to end
     * @return An {@link Iterator} for the deque.
     */
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("There are no elements left to iterate through.");
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "Cannot remove an element from deque using iterator.");
        }
    }

    /**
     * Unit testing (optional)
     * @param args
     */
    public static void main(String[] args) {}

}
