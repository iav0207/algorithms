# Algorithms

### The course
This is the implementation of the assignment from the online course _Algorithms, Part I_ by Bob Sedgewick and Kevin Wayne.
###### Links
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

### Week one – Graphs

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
