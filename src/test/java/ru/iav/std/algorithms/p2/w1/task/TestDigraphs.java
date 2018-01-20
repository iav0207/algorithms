package ru.iav.std.algorithms.p2.w1.task;

import java.util.Map;
import java.util.stream.Stream;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import static java.util.Objects.requireNonNull;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@SuppressWarnings({"unused", "WeakerAccess"})
class TestDigraphs {

    static final String
            S_ONE = "digraph1.txt",
            S_TWO = "digraph2.txt",
            S_THREE = "digraph3.txt",
            S_FOUR = "digraph4.txt",
            S_FIVE = "digraph5.txt",
            S_SIX = "digraph6.txt",
            S_NINE = "digraph9.txt",
            S_AMBI = "digraph-ambiguous-ancestor.txt";

    private static final Map<String, Digraph> DIGRAPHS = Stream.of(
            S_ONE, S_TWO, S_THREE, S_FOUR, S_FIVE, S_SIX, S_NINE, S_AMBI)
            .collect(toMap(identity(), s -> new Digraph(resourceInput(s))));

    static final Digraph
            ONE = DIGRAPHS.get(S_ONE),
            TWO = DIGRAPHS.get(S_TWO),
            THREE = DIGRAPHS.get(S_THREE),
            FOUR = DIGRAPHS.get(S_FOUR),
            FIVE = DIGRAPHS.get(S_FIVE),
            SIX = DIGRAPHS.get(S_SIX),
            NINE = DIGRAPHS.get(S_NINE),
            AMBI = DIGRAPHS.get(S_AMBI);

    private static In resourceInput(String fileName) {
        return new In(SAPTestData.class.getResource(requireNonNull(fileName)));
    }
}
