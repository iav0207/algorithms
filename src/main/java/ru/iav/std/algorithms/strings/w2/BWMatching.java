package ru.iav.std.algorithms.strings.w2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class BWMatching {

    private static class OccurrencesCountMap {
        Map<Character, TreeMap<Integer, Integer>> map = new HashMap<>();
        void add(Character c, int i) {
            map.putIfAbsent(c, new TreeMap<>());
            map.get(c).put(i, maxCount(c) + 1);
        }
        private int maxCount(Character c) {
            TreeMap<Integer, Integer> counts = map.get(c);
            return counts.isEmpty() ? 0 : counts.lastEntry().getValue();
        }
        int getCount(Character c, int i) {
            map.putIfAbsent(c, new TreeMap<>());
            Map.Entry<Integer, Integer> floor = map.get(c).floorEntry(i);
            Integer count = floor == null ? 0 : floor.getValue();
            return count == null ? 0 : count;
        }
    }

    private String bwt;
    private String[] patterns;

    BWMatching() {
    }

    BWMatching(String bwt, String[] patterns) {
        this.bwt = bwt;
        this.patterns = patterns;
        preprocessBWT();
    }

    /**
     * Occurrence counts for each character and each position in bwt,
     see the description in the comment about function preprocessBWT
     */
    private OccurrencesCountMap occCountMap = new OccurrencesCountMap();

    /**
     * Start of each character in the sorted list of characters of bwt,
     * see the description in the comment about function preprocessBWT
     */
    private Map<Character, Integer> starts = new HashMap<>();

    /**
     * Preprocess the Burrows-Wheeler Transform bwt of some text
     * and compute as a result:
     *   * starts - for each character C in bwt, starts[C] is the first position
     *       of this character in the sorted array of
     *       all characters of the text.
     *   * occ_count_before - for each character C in bwt and each position P in bwt,
     *       occ_count_before[C][P] is the number of occurrences of character C in bwt
     *       from position 0 to position P inclusive.
     */
    private void preprocessBWT() {
        for (int i = 0; i < bwt.length(); i++) {
            Character c = bwt.charAt(i);
            starts.putIfAbsent(c, i);
            occCountMap.add(c, i);
        }
    }

    /**
     * Compute the number of occurrences of string pattern in the text
     * given only Burrows-Wheeler Transform bwt of the text and additional
     * information we get from the preprocessing stage - starts and occ_counts_before.
     */
    private int countOccurrences(int i) {
        String pattern = patterns[i];
        int top = 0;
        int bottom = bwt.length() - 1;

        while (top <= bottom) {

            if (pattern.isEmpty())
                return bottom - top + 1;

            // removing last symbol from pattern
            Character c = pattern.charAt(pattern.length() - 1);
            pattern = pattern.substring(0, pattern.length() - 1);

            if (!containsInBwtBetween(c, top, bottom))
                return 0;

            // "last to first" indices transformation
            top = starts.get(c) + occCountMap.getCount(c, top);
            bottom = starts.get(c) + occCountMap.getCount(c, bottom + 1) - 1;

        }

        return 0;
    }

    private boolean containsInBwtBetween(Character c, int top, int bottom) {
        return occCountMap.getCount(c, top) != occCountMap.getCount(c, bottom);
    }

    static public void main(String[] args) throws IOException {
        new BWMatching().run();
    }

    public void print(int[] x) {
        stream(x).forEach(a -> System.out.println(a + " "));
        System.out.println();
    }

    static int[] countOccurrences(String text, String... patterns) {
        BWMatching bwMatching = new BWMatching(text, patterns);
        return IntStream.range(0, patterns.length)
                .map(bwMatching::countOccurrences)
                .toArray();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        bwt = scanner.next();
        /* Preprocess the transform once to get starts and occ_count_before.
        For each pattern, we will then use these precomputed values and
        spend only O(|pattern|) to find all occurrences of the pattern
        in the text instead of O(|pattern| + |text|).   */
        preprocessBWT();
        int patternCount = scanner.nextInt();
        patterns = new String[patternCount];
        int[] result = new int[patternCount];
        for (int i = 0; i < patternCount; ++i) {
            patterns[i] = scanner.next();
            result[i] = countOccurrences(i);
        }
        print(result);
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
