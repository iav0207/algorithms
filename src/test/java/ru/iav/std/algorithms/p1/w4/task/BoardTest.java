package ru.iav.std.algorithms.p1.w4.task;

import org.testng.annotations.Test;

import static ru.iav.std.algorithms.p1.w4.task.BoardTestData.randomBoard;

/**
 * Created by takoe on 28.02.17.
 */
public class BoardTest {

    @Test
    public void test() {
        System.out.println(-1 / 3);
    }

    @Test
    public void testToString() {
        System.out.println(randomBoard(3));
    }

    @Test
    public void testTwin() {
        Board origin = randomBoard(3);
        Board twin = origin.twin();

        System.out.println(origin);
        System.out.println(twin);
    }

    @Test
    public void testNeighbors() {
        Board board = randomBoard(5);
        System.out.println("================== Parent\n" + board);
        System.out.println("\n================== Neighbors");
        board.neighbors().forEach(System.out::println);
    }

}