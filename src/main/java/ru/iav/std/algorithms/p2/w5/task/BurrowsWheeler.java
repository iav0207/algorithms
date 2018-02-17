package ru.iav.std.algorithms.p2.w5.task;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    /**
     * Alphabet size, extended ASCII.
     */
    private static final int R = 256;

    /**
     * Bits per character.
     */
    private static final int W = 8;

    /**
     * Apply Burrows-Wheeler transform, reading from standard input and writing to standard output.
     */
    public static void transform() {
        if (BinaryStdIn.isEmpty()) {
            BinaryStdIn.close();
            return;
        }
        String text = BinaryStdIn.readString();
        int n = text.length();
        BinaryStdIn.close();

        CircularSuffixArray csa = new CircularSuffixArray(text);
        for (int i = 0; i < n; i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            int idx = (csa.index(i) - 1 + n) % n;
            BinaryStdOut.write(text.charAt(idx), W);
        }
        BinaryStdOut.close();
    }

    /**
     * Apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output.
     */
    public static void inverseTransform() {
        if (BinaryStdIn.isEmpty()) {
            BinaryStdIn.close();
            return;
        }

        // read input
        int first = BinaryStdIn.readInt();
        List<Character> t = new ArrayList<>();
        // noinspection unchecked
        TreeSet<Integer>[] st = (TreeSet<Integer>[]) new TreeSet[R];
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(W);
            t.add(c);
            if (st[c] == null) st[c] = new TreeSet<>();
            st[c].add(t.size() - 1);
        }
        int n = t.size();

        BinaryStdIn.close();

        char[] sorted = keyCountingSort(t);

        // construct next[] array
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            // noinspection ConstantConditions
            next[i] = st[sorted[i]].pollFirst();
        }

        // build text using first, t[] and next[]
        int counter = 0;
        for (int i = first; counter < n; counter++, i = next[i]) {
            BinaryStdOut.write(sorted[i], W);
        }

        BinaryStdOut.close();
    }

    private static char[] keyCountingSort(List<Character> t) {
        int N = t.size();
        int[] count = new int[R + 1];   // shifted by one
        char[] sorted = new char[N];

        for (char ti : t)       count[ti + 1]++;                // counting frequencies
        for (int r = 0; r < R;  r++) count[r + 1] += count[r];  // cumulative array
        for (char ti : t)       sorted[count[ti]++] = ti;       // fill items

        return sorted;
    }

    /**
     * If args[0] is '-', apply Burrows-Wheeler transform.
     * If args[0] is '+', apply Burrows-Wheeler inverse transform.
     */
    public static void main(String[] args) {
        String in = args[0];
        if ("-".equals(in)) {
            transform();
        } else if ("+".equals(in)) {
            inverseTransform();
        } else {
            throw new IllegalArgumentException(String.format("Unknown arg value: '%s'", in));
        }
    }
}
