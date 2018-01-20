package ru.iav.std.algorithms.p2.w1.task;

import static java.util.stream.IntStream.range;

public class Outcast {

    private final WordNet wn;

    /**
     * Constructor takes a WordNet object
     */
    public Outcast(WordNet wordNet) {
        this.wn = wordNet;
    }

    /**
     * Given an array of WordNet nouns, return an outcast.
     * Assuming that argument to {@code outcast()} contains only valid wordNet nouns
     * and that it contains at least two such nouns.
     */
    public String outcast(String[] nouns) {
        int n = nouns.length;
        int max = Integer.MIN_VALUE;
        int outcast = -1;
        for (int i = 0; i < n; i++) {
            int dist = dist(nouns, i);
            if (dist > max) {
                max = dist;
                outcast = i;
            }
        }
        return nouns[outcast];
    }

    private int dist(String[] nouns, int i) {
        return range(0, nouns.length).map(j -> j == i ? 0 : wn.distance(nouns[i], nouns[j])).sum();
    }
}
