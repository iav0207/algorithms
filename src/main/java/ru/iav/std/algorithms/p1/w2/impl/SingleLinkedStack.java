package ru.iav.std.algorithms.p1.w2.impl;

import ru.iav.std.algorithms.p1.w2.Stack;

/**
 * Created by takoe on 16.02.17.
 */
public class SingleLinkedStack<Item> implements Stack<Item> {

    private class Node {
        Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
        Item item;
        Node next;
    }

    private Node first;

    @Override
    public void push(Item item) {
        first = new Node(item, first);
    }

    @Override
    public Item pop() {
        if (first == null) {
            return null;
        }
        Item itemToRemove = first.item;
        first = first.next;
        return itemToRemove;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

}
