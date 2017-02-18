package ru.iav.std.algorithms.p1.w2.task;

import edu.princeton.cs.algs4.StdIn;

/**
 * A client program Permutation takes a command-line integer k;
 * reads in a sequence of strings from standard input using StdIn.readString();
 * and prints exactly k of them, uniformly at random.
 *
 * Print each item from the sequence at most once,
 * assuming that 0 ≤ k ≤ n, where n is the number of string
 * on standard input.
 */
public class Permutation {

    private final int k;

    private final RandomizedQueue<String> queue;

    Permutation(int k, String string) {
        this.k = k;
        queue = new RandomizedQueue<>();
        for (String s : string.split(" ")) {
            queue.enqueue(s);
        }
    }

    void printStringsUniformlyAtRandom() {
        for (int i = 0; i < k; i++) {
            System.out.println(queue.dequeue());
        }
    }

    public static void main(String... args) {
        int k = Integer.parseInt(args[0]);
        String s = StdIn.readString();
        new Permutation(k, s).printStringsUniformlyAtRandom();
    }

}
