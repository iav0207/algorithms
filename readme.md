# Algorithms

 - [Algorithms](#algorithms)
     + [About](#about)
         * [The course](#the-course)
         * [The project](#the-project)
     + [Part I](#part-i)
         * [Task one: Percolation](#task-one-percolation)
         * [Task two: Queues](#task-two-queues)
         * [Task three: Collinear points](#task-three-collinear-points)
         * [Task four: 8-Puzzle](#task-four-8-puzzle)
     + [Part II](#part-ii)
         * [Week one: Graphs](#week-one-graphs)
         * [Task one: WordNet](#task-one-wordnet)
         * [Week two: MSTs and shortest paths](#week-two-msts-and-shortest-paths)
         * [Task two: Seam carving](#task-two-seam-carving)
         * [Task three: Baseball elimination](#task-three-baseball-elimination)
         * [Task four: Boggle](#task-four-boggle)
         * [Task five: Burrows-Wheeler data compression](#task-five-burrows-wheeler-data-compression)

## About

### The course
This is the implementation of the assignment from the online course _Algorithms_ by Bob Sedgewick and Kevin Wayne.

#### Links
- Course: https://www.coursera.org/learn/algorithms-part1/home/info
- Textbook: https://algs4.cs.princeton.edu/home/
- Library: https://algs4.cs.princeton.edu/code/

### The project
The project is divided by parts and weeks - just like the course - and contains
implementations of programming tasks and some exercises I do on my own while learning
lecture material.

## Part I

### Task one: Percolation

#### Specification
The program solves a percolation problem and measures probability threshold for the percolation system
via Monte Carlo simulation.
A brief model description and simulation details can be seen at the page:
http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

### Task two: Queues

#### Specification
The code implements Deque and Randomized queue data structures.
Also, there is an executable Permutation program, returning given strings
in random order.
A description can be seen at the page:
http://coursera.cs.princeton.edu/algs4/assignments/queues.html

### Task three: Collinear points

#### Specification
The program recognizes line patterns in a given set of points, e.g. given a set of _n_ distinct
points in the plane, the algorithm finds every (maximal) line segment that connects a subset
of 4 or more of the points.
For more detail description refer to
http://coursera.cs.princeton.edu/algs4/assignments/collinear.html

### Task four: 8-Puzzle

#### Specification
The program solves the 8-puzzle and its n-by-n-generalization problem using the A*-search algorithm.
The implementation uses Priority Queue data structure to build a search tree for the problem solving.
You can see the model description and details here:
http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html

## Part II

### Week one: Graphs

- Bipartite G – G, whose vertices can be divided into two disjoint sets,
so that each vertex has edges only to ones of the opposite set.
To check if G is bipartite use DFS.
- Euler's tour – path through all the edges visiting each only once.
G has such a path if all its vertices have even degree.
- Hamilton's tour – path over all vertices visiting each only once.
Determining if there is such path is an NP-full problem.

Challenge: determine if two graphs are identical (isomorphic).
Nowadays no one knows even how difficult this problem is. 

Challenge: given a graph, can you lay it out in a plane without crossing its edges?
Use DFS-based Tarjan's planarity algorithm discovered in 1970's (rather complex).

### Task one: WordNet
#### Specification
WordNet is a semantic lexicon for the English language that is used extensively
by computational linguists and cognitive scientists;
for example, it was a key component in IBM's Watson. WordNet groups words
into sets of synonyms called synsets and describes semantic relationships between them.
One such relationship is the is-a relationship, which connects a hyponym
(more specific synset) to a hypernym (more general synset). For example,
animal is a hypernym of both bird and fish; bird is a hypernym of eagle,
pigeon, and seagull.

In the task we find shortest ancestral path between any two words
or two _sets_ of words, find words relatedness and detect outcast
among given words.

http://coursera.cs.princeton.edu/algs4/assignments/wordnet.html

### Week two: MSTs and shortest paths

Algorithms for finding shortest paths (limitations, complexity):
- Dijkstra's alg – no negative weights. _O(E+V)_
- Topological sort – no directed cycles. _O(E logV)_
- Bellman-Ford alg – no negative cycles. _O(EV)_
- Bellman-Ford queue-based modification – no negative cycles.  Best-case _O(E+V)_, worst-case _O(EV)_

### Task two: Seam carving
#### Specification
Seam-carving is a content-aware image resizing technique where
the image is reduced in size by one pixel of height (or width) at a time.
A vertical seam in an image is a path of pixels connected from the top
to the bottom with one pixel in each row. A horizontal seam is a path of pixels
connected from the left to the right with one pixel in each column.

The underlying algorithm is quite simple and elegant. Despite this fact,
this technique was not discovered until 2007 by Shai Avidan and Ariel Shamir.
It is now a feature in Adobe Photoshop (thanks to a Princeton graduate student),
as well as other popular computer graphics applications.

http://coursera.cs.princeton.edu/algs4/assignments/seam.html

### Task three: Baseball elimination
#### Specification
Given the standings in a sports division at some point during the season,
determine which teams have been mathematically eliminated from winning their division.

In the baseball elimination problem, there is a division consisting of n teams.
At some point during the season, team `i` has `w[i]` wins, `l[i]` losses,
`r[i]` remaining games, and `g[i][j]` games left to play against team `j`.
A team is mathematically eliminated if it cannot possibly finish the season
in (or tied for) first place. The goal is to determine exactly which teams
are mathematically eliminated. For simplicity, we assume that no games end in a tie
(as is the case in Major League Baseball) and that there are no rainouts,
i.e., every scheduled game is played.

http://coursera.cs.princeton.edu/algs4/assignments/baseball.html

### Task four: Boggle
#### Specification
Write a program to play the word game Boggle®.
The Boggle game. Boggle is a word game designed by Allan Turoff
and distributed by Hasbro.It involves a board made up of 16 cubic dice,
where each die has a letter printed on each of its sides.
At the beginning of the game, the 16 dice are shaken and randomly
distributed into a 4-by-4 tray, with only the top sides of the dice visible.
The players compete to accumulate points by building valid words.

The challenge is to write a Boggle solver that finds all valid words
in a given Boggle board, using a given dictionary.

http://coursera.cs.princeton.edu/algs4/assignments/boggle.html

### Task five: Burrows-Wheeler data compression
#### Specification
Implement the Burrows–Wheeler data compression algorithm.
This revolutionary algorithm outcompresses gzip and PKZIP,
is relatively easy to implement, and is not protected by any patents.
It forms the basis of the Unix compression utility bzip2.

The Burrows–Wheeler compression algorithm consists of three algorithmic components,
which are applied in succession:
1. Burrows–Wheeler transform.
Given a typical English text file,
transform it into a text file in which sequences of the same character
occur near each other many times.
1. Move-to-front encoding.
Given a text file in which sequences of the same character
occur near each other many times, convert it into a text file
in which certain characters appear more frequently than others.
1. Huffman compression.
Given a text file in which certain characters appear more frequently
than others, compress it by encoding frequently occurring characters
with short codewords and infrequently occurring characters with long codewords.

Step 3 is the one that compresses the message: it is particularly effective
because Steps 1 and 2 produce a text file in which certain characters appear
much more frequently than others. To expand a message, apply the inverse operations
in reverse order: first apply the Huffman expansion, then the move-to-front decoding,
and finally the inverse Burrows–Wheeler transform.
The task is to implement the Burrows–Wheeler and move-to-front components.

http://coursera.cs.princeton.edu/algs4/assignments/burrows.html
