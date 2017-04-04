package ru.iav.std.algorithms.strings.w1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class SuffixTree {

    private static class Node {

        private Map<Character, Node> edges = new TreeMap<>();
        private String label;
        private int index = -1;

        Node(String label) {
            this.label = label;
        }
        Node(String label, int index) {
            this(label);
            this.index = index;
        }

        void push(String s, int index) {
            char key = s.charAt(0);
            if (hasChild(key)) {

                Node oldChild = getChild(key);
                int common = commonPrefixLength(oldChild.label, s);
                Node newChild = new Node(oldChild.label.substring(0, common));
                String grandchildOneLabel = oldChild.label.substring(common);
                String grandchildTwoLabel = s.substring(common);

                if (newChild.label.length() < oldChild.label.length()) {
                    // insertion in the middle of the edge leading to old child
                    newChild.adopt(oldChild);
                } else {
                    newChild.takeAllChildrenFrom(oldChild);
                }

                if (grandchildTwoLabel.isEmpty()) {
                    newChild.terminateWith(index);
                } else {
                    newChild.push(grandchildTwoLabel, index);
                }

                if (grandchildOneLabel.isEmpty()) {
                    if (oldChild.isTerminal()) newChild.terminateWith(oldChild.index);
                } else {
                    newChild.push(grandchildOneLabel, oldChild.index);
                }

                edges.put(key, newChild);

            } else {
                edges.put(key, new Node(s, index));
            }
        }

        void adopt(Node that) {
            that.label = that.label.substring(this.label.length());
            this.edges.put(that.label.charAt(0), that);
        }

        void takeAllChildrenFrom(Node old) {
            this.edges.putAll(old.edges);
        }

        boolean hasChild(char c) {
            return getChild(c) != null;
        }
        Node getChild(char c) {
            return edges.get(c);
        }
        boolean isTerminal() {
            return index != -1 || edges.isEmpty();
        }
        void terminateWith(int index) {
            this.index = index;
        }

        private static int commonPrefixLength(String s1, String s2) {
            int i = 0;
            while (i < s1.length() && i < s2.length()) {
                if (s1.charAt(i) != s2.charAt(i)) break;
                i++;
            }
            return i;
        }
    }

    private Node root = new Node("");

    /**
     * Build a suffix tree of the string text and return a list
     * with all of the labels of its edges (the corresponding
     * substrings of the text) in any order.
     * @param text
     * @return
     */
    public List<String> computeSuffixTreeEdges(String text) {
        List<String> result = new ArrayList<>();
        buildSuffixTree(text);
        printToList(root, result);
        return result;
    }

    private void buildSuffixTree(String text) {
        for (int i = 0; i < text.length(); i++)
            root.push(text.substring(i), i);
    }

    private void printToList(Node node, List<String> list) {
        if (node.label != null && !node.label.isEmpty())
            list.add(node.label);
        node.edges.values().forEach(e -> printToList(e, list));
    }

    static public void main(String[] args) throws IOException {
        new SuffixTree().run();
    }

    public void print(List<String> x) {
        x.forEach(System.out::println);
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        List<String> edges = computeSuffixTreeEdges(text);
        print(edges);
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
