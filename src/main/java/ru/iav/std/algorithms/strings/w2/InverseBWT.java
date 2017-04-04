package ru.iav.std.algorithms.strings.w2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class InverseBWT {

    private int[] occBwt;

    private Map<Character, Integer> indexInBwt0 = new HashMap<>();

    private static class Multimap {
        private Map<Character, Integer> map = new HashMap<>();
        int add(Character c) {
            int count = count(c) + 1;
            map.put(c, count);
            return count;
        }
        private int count(Character c) {
            Integer count = map.get(c);
            return count == null ? 0 : count;
        }
    }

    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();
        String bwt0 = sort(bwt);
        computeIndices(bwt, bwt0);
        // assuming that bwt0.charAt(0) == '$',
        // the terminal symbol for the text block
        int index = 0;
        for (int i = 0; i < bwt.length() - 1; i ++) {
            result.append(bwt0.charAt(index));

            char correspondingFromBwt = bwt.charAt(index);
            int k = getOccurrenceNumberInBwt(index);
            index = getKthOccurrenceIndexInBwt0(correspondingFromBwt, k);
        }
        result.append(bwt0.charAt(index));
        return result.reverse().toString();
    }

    private void computeIndices(String bwt, String bwt0) {
        occBwt = new int[bwt.length()];
        Multimap multimapBwt = new Multimap();
        for (int i = 0; i < occBwt.length; i++) {
            Character cBwt = bwt.charAt(i);
            Character cBwt0 = bwt0.charAt(i);
            occBwt[i] = multimapBwt.add(cBwt);
            indexInBwt0.putIfAbsent(cBwt0, i);
        }
    }

    private int getKthOccurrenceIndexInBwt0(Character c, int k) {
        return indexInBwt0.get(c) + k - 1;
    }

    private int getOccurrenceNumberInBwt(int index) {
        return occBwt[index];
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
