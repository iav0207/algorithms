package ru.iav.std.algorithms.strings.w2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {

    String transform(String text) {
        StringBuilder result = new StringBuilder();
        constructSortedRotations(text).forEach(s -> result.append(lastChar(s)));
        return result.toString();
    }

    private char lastChar(String s) {
        return s.charAt(s.length() - 1);
    }

    private Set<String> constructSortedRotations(String s) {
        Set<String> rotations = new TreeSet<>();
        for (int i = 0; i < s.length(); i++) {
            rotations.add(s);
            s = rotate(s);
        }
        return rotations;
    }

    private String rotate(String s) {
        return s.substring(1) + s.charAt(0);
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(transform(text));
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
