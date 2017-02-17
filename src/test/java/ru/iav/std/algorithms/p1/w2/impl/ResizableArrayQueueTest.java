package ru.iav.std.algorithms.p1.w2.impl;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.p1.w2.Queue;

/**
 * Created by takoe on 17.02.17.
 */
public class ResizableArrayQueueTest extends QueueTest {

    @Override
    <Item> Queue<Item> newQueue() {
        return new ResizableArrayQueue<Item>();
    }

    @Override
    @Test
    public void shouldBeFIFO() throws Exception {
        super.shouldBeFIFO();
    }

}