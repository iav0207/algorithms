package ru.iav.std.algorithms.p2.w1.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public class WordNet {

    private static final String DELIMITER = ",";

    private final Map<String, SET<Integer>> nounToSynIds;
    private final Map<Integer, Synset> idToSynset;
    private final Digraph hyper;
    private final SAP sap;

    private static class Synset {
        final String name;
        final SET<String> nouns = new SET<>();

        public Synset(String name) {
            this.name = name;
            stream(name.split("\\s+")).forEach(nouns::add);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Synset synset = (Synset) o;
            return Objects.equals(name, synset.name) && Objects.equals(nouns, synset.nouns);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, nouns);
        }
    }

    /**
     * Constructor takes the name of the two input files
     */
    @SuppressWarnings("unchecked")
    public WordNet(String synsets, String hypernyms) {
        In synsetsInput = resourceInput(synsets);
        In hypernymsInput = resourceInput(hypernyms);
        nounToSynIds = new HashMap<>();
        idToSynset = new HashMap<>();
        while (synsetsInput.hasNextLine()) {
            String[] split = synsetsInput.readLine().split(DELIMITER);
            int id = parseInt(split[0]);
            Synset synset = new Synset(split[1]);
            for (String noun : synset.nouns) {
                nounToSynIds.putIfAbsent(noun, new SET<>());
                nounToSynIds.get(noun).add(id);
            }
            idToSynset.put(id, synset);
        }
        hyper = new Digraph(idToSynset.size());
        while (hypernymsInput.hasNextLine()) {
            String[] split = hypernymsInput.readLine().split(DELIMITER);
            int id = parseInt(split[0]);
            range(1, split.length).forEach(i -> hyper.addEdge(id, parseInt(split[i])));
        }
        sap = new SAP(hyper);
    }

    private static In resourceInput(String fileName) {
        return new In(WordNet.class.getResource(requireNonNull(fileName)));
    }

    /**
     * Returns all WordNet nouns
     */
    public Iterable<String> nouns() {
        return new HashSet<>(nounToSynIds.keySet());
    }

    /**
     * Is the word a WordNet noun?
     */
    public boolean isNoun(String word) {
        return nounToSynIds.containsKey(requireNonNull(word));
    }

    /**
     * Distance between nounA and nounB (defined below)
     */
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        return sap.length(nounToSynIds.get(nounA), nounToSynIds.get(nounB));
    }

    /**
     * A synset (second field of synsets.txt)
     * that is the common ancestor of nounA and nounB
     * in a shortest ancestral path (defined below)
     */
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        int anc = sap.ancestor(nounToSynIds.get(nounA), nounToSynIds.get(nounB));
        return idToSynset.get(anc).name;
    }

    private static <T> T requireNonNull(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument is null.");
        }
        return obj;
    }

}
