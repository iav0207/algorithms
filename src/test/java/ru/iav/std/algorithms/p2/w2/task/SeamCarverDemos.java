package ru.iav.std.algorithms.p2.w2.task;

import org.testng.annotations.Test;

import static java.util.Arrays.stream;

public class SeamCarverDemos {

    @SuppressWarnings({"InfiniteLoopStatement", "StatementWithEmptyBody"})
    @Test
    public void resizeChameleon() {
        ResizeDemo.main(args("chameleon.png", 100, 0));
        while (true) {}
    }

    @SuppressWarnings({"InfiniteLoopStatement", "StatementWithEmptyBody"})
    @Test
    public void resizeOcean() {
        ResizeDemo.main(args("HJocean.png", 100, 0));
        while (true) {}
    }

    @Test
    public void printSeams10x10() {
        PrintSeams.main(args("10x10.png"));
    }

    private static String[] args(Object... s) {
        return stream(s).map(Object::toString).toArray(String[]::new);
    }

}
