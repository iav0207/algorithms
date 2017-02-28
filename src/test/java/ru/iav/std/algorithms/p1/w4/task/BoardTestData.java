package ru.iav.std.algorithms.p1.w4.task;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by takoe on 28.02.17.
 */
public class BoardTestData {

    Random random = ThreadLocalRandom.current();

    public static Board randomBoard(int n) {
        int[] array = IntStream.range(0, n*n).toArray();
        StdRandom.shuffle(array);
        int[][] blocks = new int[n][n];
        int k = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = array[k++];
        return new Board(blocks);
    }

}
