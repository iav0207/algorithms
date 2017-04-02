package ru.iav.std.algorithms.strings.w1;

import java.io.*;
import java.util.*;

public class TrieMatching implements Runnable {

	private static class Node {

		private Map<Character, Node> children = new TreeMap<>();

		Node getOrCreateChild(char c) {
			children.putIfAbsent(c, new Node());
			return getChild(c);
		}
		boolean hasChild(char c) {
			return getChild(c) != null;
		}
		Node getChild(char c) {
			return children.get(c);
		}
		boolean isLeaf() {
			return children.isEmpty();
		}
	}

	private Node root = new Node();

	List <Integer> solve (String text, List <String> patterns) {
		List <Integer> result = new ArrayList <> ();
		buildTrie(patterns);
		for (int start = 0; start < text.length(); start++) {
			if (hasMatch(text, start)) result.add(start);
		}
		return result;
	}

	private void buildTrie(List<String> patterns) {
		for (String pattern : patterns) {
			Node node = root;
			for (Character c : pattern.toCharArray()) {
				node = node.getOrCreateChild(c);
			}
		}
	}

	private boolean hasMatch(String text, int start) {
		Node node = root;
		for (int i = start; i < text.length(); i++) {
			char c = text.charAt(i);
			if (!node.hasChild(c)) return false;
			node = node.getChild(c);
			if (node.isLeaf()) return true;
		}
		return false;
	}

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatching ()).start ();
	}
}
