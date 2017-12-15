package ru.iav.std.algorithms.my;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The idea is to invent a bicycle: implement LRU cache not using {@link LinkedHashMap}.
 *
 * @param <K> keys type
 * @param <E> values type
 */
@SuppressWarnings("unused")
public class LruCache<K, E> {

    private static class Node<K, E> {
        final K key;
        final E value;
        K next, prev;
        Node(K key, E value) {
            this.key = key;
            this.value = value;
        }
        Node<K, E> withPrev(K prev) {
            this.prev = prev;
            return this;
        }
        Node<K, E> withNext(K next) {
            this.next = next;
            return this;
        }
    }

    private final int maxSize;
    private final Map<K, Node<K, E>> map;
    private Node<K, E> head, tail;

    public LruCache(int maxSize) {
        this.maxSize = maxSize;
        this.map = new HashMap<>(maxSize);
    }

    public void put(K key, E element) {
        final Node<K, E> fresh;
        if (map.containsKey(key)) {
            Node<K, E> oldNode = map.get(key);
            map.get(oldNode.prev).next = oldNode.next;
            map.get(oldNode.next).prev = oldNode.prev;
            fresh = oldNode.value.equals(element) ? oldNode : new Node<>(key, element);
        } else {
            fresh = new Node<>(key, element);
        }
        push(fresh);
        dropLruIfNeeded();
    }

    public E get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node<K, E> node = map.get(key);
        push(node);
        return node.value;
    }

    private void push(Node<K, E> node) {
        final Node<K, E> newHead;
        if (head == null) {
            newHead = tail = node;
        } else if (head == tail) {
            newHead = node.withNext(tail.key);
            tail.withPrev(newHead.key);
        } else {
            head.withPrev(node.key);
            newHead = node.withNext(head.key);
        }
        head = newHead;
        map.put(node.key, node);
    }

    private void dropLruIfNeeded() {
        if (map.size() <= maxSize) {
            return;
        }
        Node<K, E> newTail = map.get(tail.prev);
        map.remove(tail.withPrev(null).key);
        tail = newTail.withNext(null);
    }

}
