package ru.iav.std.algorithms.p2.w4.task;

import edu.princeton.cs.algs4.StdOut;
import org.testng.annotations.Test;

import static ru.iav.std.algorithms.p2.w4.task.ResUtil.getResourceAsFile;

public class BoggleBoardTest {

    @Test
    public void testBoard() {
        // initialize a 4-by-4 board using Hasbro dice
        StdOut.println("Hasbro board:");
        BoggleBoard board1 = new BoggleBoard();
        StdOut.println(board1);
        StdOut.println();

        // initialize a 4-by-4 board using letter frequencies in English language
        StdOut.println("Random 4-by-4 board:");
        BoggleBoard board2 = new BoggleBoard(4, 4);
        StdOut.println(board2);
        StdOut.println();

        // initialize a 4-by-4 board from a 2d char array
        StdOut.println("4-by-4 board from 2D character array:");
        char[][] a =  {
                { 'D', 'O', 'T', 'Y' },
                { 'T', 'R', 'S', 'F' },
                { 'M', 'X', 'M', 'O' },
                { 'Z', 'A', 'B', 'W' }
        };
        BoggleBoard board3 = new BoggleBoard(a);
        StdOut.println(board3);
        StdOut.println();

        // initialize a 4-by-4 board from a file
        String filename = getResourceAsFile("board-quinquevalencies.txt").getAbsolutePath();
        StdOut.println("4-by-4 board from file " + filename + ":");
        BoggleBoard board4 = new BoggleBoard(filename);
        StdOut.println(board4);
        StdOut.println();
    }
}
