package ru.iav.std.algorithms.p1.w2.impl;

import ru.iav.std.algorithms.p1.w2.Queue;

/**
 * Created by takoe on 16.02.17.
 */
public class SingleLinkedQueue<Item> implements Queue<Item> {

    private class Node {
        Node next;
        Item item;
        Node(Item item) {
            this.item = item;
        }
    }

    private Node first, last;

    @Override
    public void enqueue(Item item) {
        if (isEmpty()) {
            first = last = new Node(item);
            return;
        }
        Node oldLast = last;
        last = new Node(item);
        oldLast.next = last;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) return null;
        Item itemToRemove = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return itemToRemove;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

}
