package ru.iav.std.algorithms.p1.w4.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;

/**
 * Created by takoe on 28.02.17.
 */
public class Board {

    private final int[][] blocks;

    private final int n;

    /**
     * Construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
     * @param blocks
     */
    public Board(int[][] blocks) {
        validate(blocks);       // performance?
        n = blocks.length;
        this.blocks = copy(blocks);
    }

    private static void validate(int[][] inputMatrix) {
        Objects.requireNonNull(inputMatrix, "Input array must be non-null.");
        int n = inputMatrix.length;
        for (int[] eachVector : inputMatrix)
            if (eachVector.length != n)
                throw new IllegalArgumentException("Input array is not a NxN matrix");
    }

    private static int[][] copy(int[][] matrix) {
        int n = matrix.length;
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++)
            copy[i] = Arrays.copyOf(matrix[i], n);
        return copy;
    }

    /**
     * Board dimension n
     * @return
     */
    public int dimension() {
        return n;
    }

    /**
     * Hamming priority function.
     * The number of blocks in the wrong position, plus the number of moves made so far to get to the search node.
     * Intuitively, a search node with a small number of blocks in the wrong position is close to the goal,
     * and we prefer a search node that have been reached using a small number of moves.
     * @return number of blocks out of place
     */
    public int hamming() {
        return countBlocksInWrongPosition();
    }

    /**
     * Manhattan priority function. The sum of the Manhattan distances (sum of the vertical and horizontal distance)
     * from the blocks to their goal positions, plus the number of moves made so far to get to the search node.
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int k = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                k += distanceToGoalPosition(i, j);
        return k;
    }

    private int distanceToGoalPosition(int i, int j) {
        int v = blocks[i][j];
        if (v == 0) return 0;
        return abs(i - iGoal(v)) + abs(j - jGoal(v));
    }

    /**
     * Is this board the goal board?
     * @return
     */
    public boolean isGoal() {
        return countBlocksInWrongPosition() == 0;
    }

    private int countBlocksInWrongPosition() {
        int k = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (blocks[i][j] != 0 && blocks[i][j] != goalValue(i, j))
                    k++;
        return k;
    }

    private int jGoal(int v) {
        return v == 0 ? n - 1 : (v - 1) % n;
    }

    private int iGoal(int v) {
        return v == 0 ? n - 1 : (v - 1) / n;
    }

    private int goalValue(int i, int j) {
        return ((n * i) + j + 1) % (n * n);
    }

    /**
     * A board that is obtained by exchanging any pair of blocks
     * @return
     */
    public Board twin() {
        int[][] twinBlocks = copy(blocks);
        if (twinBlocks[0][0] == 0)          swap(twinBlocks, 0, 1, 1, 1);
        else if (twinBlocks[0][1] == 0)     swap(twinBlocks, 0, 0, 1, 0);
        else                                swap(twinBlocks, 0, 0, 0, 1);
        return new Board(twinBlocks);
    }

    /**
     * Does this board equal y?
     * @param y
     * @return
     */
    @Override
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;

        Board board = (Board) y;

        if (n != board.n) return false;
        return Arrays.deepEquals(blocks, board.blocks);

    }

    /**
     * All neighboring boards
     * @return
     */
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int zero = locateZero();
        int jZero = jGoal(zero);
        int iZero = iGoal(zero);
        if (iZero > 0)              neighbors.add(createTopNeighbor(iZero, jZero));
        if (iZero < n - 1)          neighbors.add(createBottomNeighbor(iZero, jZero));
        if (jZero > 0)              neighbors.add(createLeftNeighbor(iZero, jZero));
        if (jZero < n - 1)          neighbors.add(createRightNeighbor(iZero, jZero));
        return neighbors;
    }

    private int locateZero() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (blocks[i][j] == 0) {
                    return goalValue(i, j);
                }
        return -1;
    }

    private Board createTopNeighbor(int iZero, int jZero) {
        int[][] newBlocks = copy(blocks);
        swap(newBlocks, iZero, jZero, iZero - 1, jZero);
        return makeMove(newBlocks);
    }

    private Board createBottomNeighbor(int iZero, int jZero) {
        int[][] newBlocks = copy(blocks);
        swap(newBlocks, iZero, jZero, iZero + 1, jZero);
        return makeMove(newBlocks);
    }

    private Board createLeftNeighbor(int iZero, int jZero) {
        int[][] newBlocks = copy(blocks);
        swap(newBlocks, iZero, jZero, iZero, jZero - 1);
        return makeMove(newBlocks);
    }

    private Board createRightNeighbor(int iZero, int jZero) {
        int[][] newBlocks = copy(blocks);
        swap(newBlocks, iZero, jZero, iZero, jZero + 1);
        return makeMove(newBlocks);
    }

    private Board makeMove(int[][] newBlocks) {
        return new Board(newBlocks);
    }

    private static void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int swap = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = swap;
    }

    /**
     * String representation of this board (in the output format specified below)
     * @return
     */
    public String toString() {
        final char newLine = '\n', tab = '\t';
        StringBuilder sb = new StringBuilder(String.valueOf(n)).append(newLine);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(tab).append(String.valueOf(blocks[i][j]));
            sb.append(newLine);
        }
        return sb.toString();
    }

    /**
     * Unit tests (not graded)
     * @param args
     */
    public static void main(String[] args) {}
}
