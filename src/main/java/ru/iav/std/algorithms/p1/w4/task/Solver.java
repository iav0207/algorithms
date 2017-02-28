package ru.iav.std.algorithms.p1.w4.task;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.net.URL;

import static java.util.Objects.requireNonNull;

/**
 * Created by takoe on 28.02.17.
 */
public class Solver {

    private static class Node implements Comparable<Node> {
        final Node parent;
        final Board board;
        final int move;
        static Node initial(Board board) {
            return new Node(board, null, 0);
        }
        Node(Board board, Node parent, int move) {
            this.board = board;
            this.parent = parent;
            this.move = move;
        }
        public Node child(Board board) {
            return new Node(board, this, move + 1);
        }
        @Override
        public int compareTo(Node that) {
            return Integer.compare(this.hamming(), that.hamming());
        }
        private int hamming() {
            return board.hamming() + move;
        }
        private int manhattan() {
            return board.manhattan() + move;
        }
    }

    private Node goal;

    /**
     * Find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(Board initial) {
        requireNonNull(initial, "The constructor argument must be non-null.");

        final MinPQ<Node> pq = new MinPQ<>();
        final MinPQ<Node> twinPq = new MinPQ<>();

        pq.insert(Node.initial(initial));
        twinPq.insert(Node.initial(initial.twin()));

        while (!pq.isEmpty() && !twinPq.isEmpty()) {

            goal = step(pq);
            if (goal != null)           break;  // solved
            if (step(twinPq) != null)   break;  // unsolvable

        }
    }

    /**
     * Deletes min node and, if it is a goal, returns it.
     * If not, adds all possible children to the queue and returns null.
     * @param pq priority queue to make a step.
     * @return a dequeued {@link Node}, if it is goal, else <tt>null</tt>.
     */
    private Node step(MinPQ<Node> pq) {
        if (pq.isEmpty()) return null;

        Node current = pq.delMin();
        if (current.board.isGoal()) {
            return current;
        }
        Node parent = current.parent;
        for (Board neighbor : current.board.neighbors()) {
            if (parent == null || !parent.board.equals(neighbor)) // critical optimization
                pq.insert(current.child(neighbor));
        }
        return null;
    }

    /**
     * Is the initial board solvable?
     * @return
     */
    public boolean isSolvable() {
        return goal != null;
    }

    /**
     * Min number of moves to solve initial board; -1 if unsolvable
     * @return
     */
    public int moves() {
        Node current = goal;
        int count = -1;
        while (current != null) {
            current = current.parent;
            count++;
        }
        return count;
    }

    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     * @return
     */
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Stack<Board> solution = new Stack<>();
        Node current = goal;
        while (current != null) {
            solution.push(current.board);
            current = current.parent;
        }
        return solution;
    }

    /**
     * Solve a slider puzzle (given below)
     * @param args
     */
    public static void main(String[] args) {

        // create initial board from file
        String inputFileName = args[0];
        URL resource = Solver.class.getResource(requireNonNull(inputFileName));
        In in = new In(resource);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
