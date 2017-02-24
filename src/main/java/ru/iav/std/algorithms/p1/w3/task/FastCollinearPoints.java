package ru.iav.std.algorithms.p1.w3.task;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *  Examines 4 points at a time and checks whether they all
 *  lie on the same line segment, returning all such line segments.
 *  To check whether the 4 points p, q, r, and s are collinear,
 *  check whether the three slopes between p and q, between p and r,
 *  and between p and s are all equal.
 */
public class FastCollinearPoints {

    private LineSegment[] segments = new LineSegment[] {};

    private Point[] points, aux;

    /**
     * Finds all line segments containing 4 or more points
     */
    public FastCollinearPoints(Point[] points) {
        validate(points);
        this.points = points;
        aux = new Point[points.length];
        System.arraycopy(points, 0, aux, 0, points.length);
        segments = findDistinctSegments();
    }

    private LineSegment[] findDistinctSegments() {
        int n = points.length;
        SortedSet<LazyLineSegment> lazyLineSegments = new TreeSet<>();
        for (Point p : points) {
            Arrays.sort(aux, p.slopeOrder());
            int i = 1;      // beginning with i = 1 because aux[i = 0] is always p itself
            int j = i + 1;
            while (i < n - 2 && j < n) {
                if (slope(p, j) != slope(p, i)) {
                    if (enoughPoints(i, j)) {
                        lazyLineSegments.add(new LazyLineSegment(p, Arrays.copyOfRange(aux, i, j)));
                    }
                    i = j;
                }
                j++;
            }
            // j == n
            if (enoughPoints(i, j))
                lazyLineSegments.add(new LazyLineSegment(p, Arrays.copyOfRange(aux, i, j)));
        }
        return mapToArray(lazyLineSegments);
    }

    private boolean enoughPoints(int i, int j) {
        return j - i > 2;
    }

    private double slope(Point p, int i) {
        double slope = p.slopeTo(aux[i]);
        if (slope == Double.NEGATIVE_INFINITY)
            throw new IllegalArgumentException("There must be no duplicate points.");
        return slope;
    }

    private LineSegment[] mapToArray(Set<LazyLineSegment> set) {
        LineSegment[] array = new LineSegment[set.size()];
        Iterator<LazyLineSegment> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext())
            array[i++] = iterator.next().create();
        return array;
    }

    private static void validate(Point[] points) {
        Objects.requireNonNull(points, "Constructor argument may not be null.");
        for (Point each : points)
            Objects.requireNonNull(each, "There must be no null elements in the input array.");
    }

    /**
     * The number of line segments
     */
    public int numberOfSegments() {
        return segments.length;
    }

    /**
     * The line segments
     */
    public LineSegment[] segments() {
        return segments;
    }

    private static class LazyLineSegment implements Comparable<LazyLineSegment> {
        private SortedSet<Point> points;
        LazyLineSegment(Point p, Point... points) {
            this.points = new TreeSet<>(Arrays.asList(points));
            this.points.add(p);
        }
        LineSegment create() {
            return new LineSegment(min(), max());
        }
        private Point min() {
            return points.first();
        }
        private Point max() {
            return points.last();
        }
        @Override
        public int compareTo(LazyLineSegment that) {
            int slopes = Double.compare(this.slope(), that.slope());
            if (slopes != 0) return slopes;
            if (isTheSameLine(that)) return 0;
            return this.min().compareTo(that.min());
        }
        private boolean isTheSameLine(LazyLineSegment that) {
            return isOnThisLine(that.min()) && isOnThisLine(that.max());
        }
        private boolean isOnThisLine(Point p) {
            double slopeMinToP = min().slopeTo(p);
            return slopeMinToP == slope()
                    || slopeMinToP == Double.NEGATIVE_INFINITY;
        }
        private double slope() {
            return min().slopeTo(max());
        }
    }

}
