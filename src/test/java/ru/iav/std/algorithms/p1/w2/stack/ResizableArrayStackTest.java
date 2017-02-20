package ru.iav.std.algorithms.p1.w2.stack;

import ru.iav.std.algorithms.p1.w2.Stack;

/**
 * Created by takoe on 16.02.17.
 */
public class ResizableArrayStackTest extends StackTest {

    @Override
    <Item> Stack<Item> newStack() {
        return new ResizableArrayStack<Item>();
    }

}