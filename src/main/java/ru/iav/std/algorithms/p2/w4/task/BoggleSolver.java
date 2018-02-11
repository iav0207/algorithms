package ru.iav.std.algorithms.p2.w4.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import edu.princeton.cs.algs4.TST;

public class BoggleSolver {

    private static final int[] SCORE_TABLE = new int[] {0, 0, 0, 1, 1, 2, 3, 5, 11};
    private static final Object NONE = new Object();

    private final TST<Integer> dictionary = new TST<>();

    /**
     * Initializes the data structure using the given array of strings as the dictionary.
     * (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
     */
    public BoggleSolver(String[] dictionary) {
        for (String word : requireNonNull(dictionary)) {
            this.dictionary.put(word, SCORE_TABLE[Math.min(SCORE_TABLE.length - 1, word.length())]);
        }
    }

    /**
     * Returns the score of the given word if it is in the dictionary, zero otherwise.
     * (You can assume the word contains only the uppercase letters A through Z.)
     */
    public int scoreOf(String word) {
        Integer score = dictionary.get(requireNonNull(word));
        return score == null ? 0 : score;
    }

    /**
     * Returns the set of all valid words in the given Boggle board, as an Iterable.
     */
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        requireNonNull(board);
        return new BoardWalker(requireNonNull(board)).words();
    }

    private class BoardWalker {
        private final BoggleBoard board;
        private final TST<Object> words = new TST<>();

        BoardWalker(BoggleBoard board) {
            this.board = board;
            for (int i = 0; i < board.rows(); i++)
                for (int j = 0; j < board.cols(); j++)
                    walk(new Path(Collections.singleton(toIdx(i, j)), String.valueOf(board.getLetter(i, j))));
        }

        Iterable<String> words() {
            return words.keys();
        }

        private void walk(Path p) {
            String word = p.s.replaceAll("Q", "QU");
            if (word.length() > 2 && dictionary.contains(word))
                words.put(word, NONE);
            for (Field neighbour : neighbours(p.last)) {
                if (!p.canAdd(neighbour.idx))
                    continue;
                String nc = neighbour.c == 'Q' ? "QU" : String.valueOf(neighbour.c);
                if (word.length() < 3 || dictionary.keysWithPrefix(word + nc).iterator().hasNext())
                    walk(new Path(p).withNext(neighbour));
            }
        }

        private List<Field> neighbours(int idx) {
            List<Field> ns = new ArrayList<>();
            int i = toI(idx), j = toJ(idx);
            for (int ii = i - 1; ii < i + 2; ii++)
                for (int jj = j - 1; jj < j + 2; jj++)
                    if (ii >= 0 && jj >= 0 && ii < board.rows() && jj < board.cols() && (ii != i || jj != j))
                        ns.add(new Field(toIdx(ii, jj), board.getLetter(ii, jj)));
            return ns;
        }

        private int toIdx(int i, int j) {
            return board.cols() * i + j;
        }
        private int toI(int idx) {
            return idx / board.cols();
        }
        private int toJ(int idx) {
            return idx % board.cols();
        }
    }

    private static class Path {
        private final LinkedHashSet<Integer> indices = new LinkedHashSet<>();
        private String s;
        private int last;

        Path(Path p) {
            this(p.indices, p.s);
        }
        Path(Collection<Integer> indices, String s) {
            checkArgument(!indices.isEmpty(), "Path should not be empty");
            this.s = s;
            for (int idx : indices) {
                this.indices.add(idx);
                last = idx;
            }
            checkArgument(indices.size() == this.indices.size(), "Unique elements expected");
            checkArgument(indices.size() == s.length(), "String length doesn't match the path length");
        }
        boolean canAdd(int idx) {
            return !indices.contains(idx);
        }
        Path withNext(Field f) {
            checkArgument(indices.add(f.idx), "This index is already present in the path");
            s += String.valueOf(f.c);
            last = f.idx;
            return this;
        }
    }

    private static class Field {
        private final int idx;
        private final char c;

        Field(int idx, char c) {
            this.idx = idx;
            this.c = c;
        }
    }

    private static void checkArgument(boolean condition, String msg) {
        if (!condition) {
            throw new IllegalArgumentException(msg);
        }
    }

    private static <T> T requireNonNull(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        return obj;
    }
}
