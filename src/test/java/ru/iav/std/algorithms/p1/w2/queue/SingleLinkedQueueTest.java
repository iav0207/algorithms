package ru.iav.std.algorithms.p1.w2.queue;

import ru.iav.std.algorithms.p1.w2.Queue;

/**
 * Created by takoe on 17.02.17.
 */
public class SingleLinkedQueueTest extends QueueTest {

    @Override
    <Item> Queue<Item> newQueue() {
        return new SingleLinkedQueue<Item>();
    }

}