package ru.iav.std.algorithms.p2.w5.task;

import java.util.Arrays;

public class CircularSuffixArray {

    private final int[] index;

    /**
     * Circular suffix array of s.
     */
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException("String must be non-null.");
        }
        index = new int[s.length()];
        CircularSuffix[] suffixes = new CircularSuffix[s.length()];
        for (int i = 0; i < s.length(); i++)
            suffixes[i] = new CircularSuffix(s, i);
        Arrays.sort(suffixes);
        for (int i = 0; i < suffixes.length; i++) {
            index[i] = suffixes[i].index;
        }
    }

    private static class CircularSuffix implements Comparable<CircularSuffix> {
        private final String text;
        private final int index;

        private CircularSuffix(String text, int index) {
            this.text = text;
            this.index = index;
        }
        private int length() {
            return text.length();
        }
        private char charAt(int i) {
            return text.charAt((index + i) % length());
        }

        public int compareTo(CircularSuffix that) {
            if (this == that) return 0;  // optimization
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return this.length() - that.length();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length(); i++)
                sb.append(charAt(i));
            return sb.toString();
        }
    }

    /**
     * Length of s.
     */
    public int length() {
        return index.length;
    }

    /**
     * Returns index of ith sorted suffix.
     */
    public int index(int i) {
        if (i < 0 || i >= length()) {
            throw new IllegalArgumentException("String must be non-null.");
        }
        return index[i];
    }

    /**
     * Unit testing (required)
     */
    public static void main(String[] args) {
        String in = args[0];
        CircularSuffixArray csa = new CircularSuffixArray(in);
        System.out.println(csa.length());
        for (int i = 0; i < in.length(); i++) {
            System.out.println(csa.index(i));
        }
    }
}
