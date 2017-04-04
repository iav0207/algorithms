package ru.iav.std.algorithms.strings.w2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class InverseBWT {

    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();
        String bwt0 = sort(bwt);
        // assuming that bwt0.charAt(0) == '$',
        // the terminal symbol for the text block
        int index = 0;
        for (int i = 0; i < bwt.length() - 1; i ++) {
            result.append(bwt0.charAt(index));

            char correspondingFromBwt = bwt.charAt(index);
            int k = getOccurrenceNumber(bwt, index);
            index = getKthOccurrenceIndex(bwt0, correspondingFromBwt, k);
        }
        result.append(bwt0.charAt(index));
        return result.reverse().toString();
    }

    private int getKthOccurrenceIndex(String s, Character c, int k) {
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = s.indexOf(c, max(0, result + 1));
        }
        return result;
    }

    int getOccurrenceNumber(String s, int index) {
        char c = s.charAt(index);
        return (int) s.substring(0, index + 1).chars()
                .filter(codePoint -> codePoint == (int) c)
                .count();
    }

    private String sort(String s1) {
        return s1.chars().sorted()
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append).toString();
    }

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
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
