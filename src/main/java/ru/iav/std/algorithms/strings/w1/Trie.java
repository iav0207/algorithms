package ru.iav.std.algorithms.strings.w1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Trie {

    private List<Map<Character, Integer>> trie = new ArrayList<>();

    public Trie() {
        addNewEmptyNode();
    }

    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        Arrays.stream(patterns).forEach(this::addToTrie);
        return trie;
    }

    private void addToTrie(String pattern) {
        int node = 0;
        for (Character c : pattern.toCharArray()) {
            node = addChildIfAbsent(node, c);
        }
    }

    private Integer addChildIfAbsent(Integer node, Character child) {
        if (trie.get(node).get(child) == null) {
            trie.get(node).put(child, trie.size());
            addNewEmptyNode();
            return lastAddedNode();
        }
        return trie.get(node).get(child);
    }

    private void addNewEmptyNode() {
        trie.add(new TreeMap<>());
    }

    private int lastAddedNode() {
        return trie.size() - 1;
    }

    static public void main(String[] args) throws IOException {
        new Trie().run();
    }

    public void print(List<Map<Character, Integer>> trie) {
        System.out.print(toString(trie));
    }

    private String toString(List<Map<Character, Integer>> trie) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> e : node.entrySet()) {
                sb.append(String.format("%d->%d:%s\n", i, e.getValue(), e.getKey()));
            }
        }
        if (sb.length() > 1) sb.deleteCharAt(sb.length() - 1);   // removing last \n
        return sb.toString();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }

    String run(String s) {
        return toString(buildTrie(s.split("\n")));
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
