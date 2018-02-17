package ru.iav.std.algorithms.p2.w5.task;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    /**
     * Alphabet size of extended ASCII.
     */
    private static final int R = 256;

    /**
     * Number of bits per character.
     */
    private static final int W = 8;

    private final char[] a;

    public MoveToFront() {
        a = new char[R];
        for (int i = 0; i < R; i++) {
            a[i] = (char) i;
        }
    }

    /**
     * Apply move-to-front encoding, reading from standard input and writing to standard output.
     */
    public static void encode() {
        new MoveToFront().enc();
    }

    private void enc() {
        char[] in = BinaryStdIn.readString().toCharArray();
        BinaryStdIn.close();
        for (char c : in) {
            for (int j = 0; j < R; j++) {
                if (c == a[j]) {
                    BinaryStdOut.write(j, W);
                    rotateArray(j);
                    break;
                }
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Apply move-to-front decoding, reading from standard input and writing to standard output.
     */
    public static void decode() {
        new MoveToFront().dec();
    }

    private void dec() {
        while (!BinaryStdIn.isEmpty()) {
            int n = BinaryStdIn.readInt(W);
            BinaryStdOut.write(a[n], W);
            rotateArray(n);
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    /**
     * Rotate a[0..k] sub-array, moving k-th element to front, e.g. 0,
     * and shifting elements [0..k-1].
     */
    private void rotateArray(int k) {
        if (k == 0) return;
        char toMove = a[k];
        System.arraycopy(a, 0, a, 1, k);
        a[0] = toMove;
    }

    /**
     * If args[0] is '-', apply move-to-front encoding.
     * If args[0] is '+', apply move-to-front decoding.
     */
    public static void main(String[] args) {
        String in = args[0];
        if ("-".equals(in)) {
            encode();
        } else if ("+".equals(in)) {
            decode();
        } else {
            throw new IllegalArgumentException(String.format("Unknown arg value: '%s'", in));
        }
    }
}
