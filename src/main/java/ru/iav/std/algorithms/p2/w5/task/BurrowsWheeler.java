package ru.iav.std.algorithms.p2.w5.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import static java.lang.String.format;

public class BurrowsWheeler {

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
        HashMap<Character, TreeSet<Integer>> st = new HashMap<>();
        while (!BinaryStdIn.isEmpty()) {
            Character c = BinaryStdIn.readChar(W);
            int idx = t.size();
            t.add(c);
            st.computeIfAbsent(c, ch -> new TreeSet<>()).add(idx);
        }

        // close input
        BinaryStdIn.close();

        List<Character> sorted = new ArrayList<>(t);
        Collections.sort(sorted);

        // construct next[] array
        int[] next = new int[t.size()];
        for (int i = 0; i < t.size(); i++) {
            Character c = sorted.get(i);
            // noinspection ConstantConditions
            next[i] = st.get(c).pollFirst();
        }

        // build text using first, t[] and next[]
        int count = 0;
        for (int i = first; count < t.size(); count++, i = next[i]) {
            BinaryStdOut.write(sorted.get(i), W);
        }

        BinaryStdOut.close();
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
        }
        throw new IllegalArgumentException(format("Unknown arg value: '%s'", in));
    }
}
