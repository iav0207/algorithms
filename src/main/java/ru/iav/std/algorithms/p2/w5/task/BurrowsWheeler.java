package ru.iav.std.algorithms.p2.w5.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        int[] st = new int[R];  // maps character to its least index in t[]
        Arrays.fill(st, -1);
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(W);
            int idx = t.size();
            t.add(c);
            if (st[c] == -1) st[c] = idx;
        }
        int n = t.size();

        BinaryStdIn.close();

        char[] sorted = keyCountingSort(t);

        // construct next[] array
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            char ci = sorted[i];
            while (t.get(st[ci]) != ci) // means that st[ci] was previously shifted after usage (next comment)
                st[ci]++;
            next[i] = st[ci]++;     // start from the next index of char ci the next time
        }

        // build text using first, t[] and next[]
        int counter = 0;
        for (int i = first; counter < n; counter++, i = next[i]) {
            BinaryStdOut.write(sorted[i], W);
        }

        BinaryStdOut.close();
    }

    /**
     * Complexity O(R+N)
     */
    private static char[] keyCountingSort(List<Character> t) {
        int n = t.size();
        int[] count = new int[R + 1];   // shifted by one
        char[] sorted = new char[n];

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
