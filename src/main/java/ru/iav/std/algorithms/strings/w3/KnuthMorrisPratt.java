package ru.iav.std.algorithms.strings.w3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KnuthMorrisPratt {

    private static final char delimiter = '$';

    private int patternLength;

    private char[] p;

    /** Prefix function */
    private int[] s;

    private List<Integer> positions;

    // Find all the occurrences of the pattern in the text and return
    // a list of all positions in the text (starting from 0) where
    // the pattern starts in the text.
    public List<Integer> findPattern(String pattern, String text) {
        positions = new ArrayList<>();
        patternLength = pattern.length();
        p = (pattern + delimiter + text).toLowerCase().toCharArray();

        computePrefixFunction();

        return positions;
    }

    private void computePrefixFunction() {
        s = new int[p.length];
        int border = 0;
        for (int i = 1; i < s.length; i++) {

            while (border > 0 && p[i] != p[border])
                border = s[border - 1];

            if (p[i] == p[border])  border++;
            else                    border = 0;

            s[i] = border;

            if (i > patternLength && border >= patternLength)
                positions.add(i - 2*patternLength);

        }
    }

    static public void main(String[] args) throws IOException {
        new KnuthMorrisPratt().run();
    }

    public void printPositions() {
        positions.forEach(a -> System.out.print(a + " "));
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String pattern = scanner.next();
        String text = scanner.next();
        findPattern(pattern, text);
        printPositions();
    }

    private static class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

}
