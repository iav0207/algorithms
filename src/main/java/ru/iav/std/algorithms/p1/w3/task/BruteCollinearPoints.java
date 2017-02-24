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
public class BruteCollinearPoints {

    private LineSegment[] segments;

    private Point[] points;

    /**
     * Finds all line segments containing 4 points
     */
    public BruteCollinearPoints(Point[] points) {
        validate(points);
        this.points = defensiveCopy(points);
        segments = findDistinctSegments();
    }

    private static <T> T[] defensiveCopy(T[] array) {
        return Arrays.copyOf(array, array.length);
    }

    private LineSegment[] findDistinctSegments() {
        int n = points.length;
        SortedSet<LazyLineSegment> lazyLineSegments = new TreeSet<>();
        for (int p = 0; p < n; p++) {
            Q_LOOP: for (int q = p + 1; q < n; q++) {
                double pqSlope = slope(p, q);
                for (int r = q + 1; r < n; r++) {
                    double prSlope = slope(p, r);
                    assertNotDuplicate(q, r);
                    for (int s = r + 1; s < n; s++) {
                        double psSlope = slope(p, s);
                        assertNotDuplicate(q, s);
                        assertNotDuplicate(r, s);
                        if (areEqual(pqSlope, prSlope, psSlope)) {
                            lazyLineSegments.add(new LazyLineSegment(
                                    points[p], points[q], points[r], points[s]));
                            continue Q_LOOP; // assuming that input does not contain line segments of 5 or more points
                        }
                    }
                }
            }
        }
        return mapToArray(lazyLineSegments);
    }

    private double slope(int p, int q) {
        double slope = points[p].slopeTo(points[q]);
        if (slope == Double.NEGATIVE_INFINITY) {
            throw duplicityException();
        }
        return slope;
    }

    private void assertNotDuplicate(int p, int q) {
        if (points[p].slopeTo(points[q]) == Double.NEGATIVE_INFINITY)
            throw duplicityException();
    }

    private static IllegalArgumentException duplicityException() {
        return new IllegalArgumentException("There must be no duplicate points.");
    }

    private static boolean areEqual(double slopeOne, double slopeTwo, double... other) {
        boolean equal = Double.compare(slopeOne, slopeTwo) == 0;
        for (double each : other)
            equal &= Double.compare(slopeOne, each) == 0;
        return equal;
    }

    private static LineSegment[] mapToArray(Set<LazyLineSegment> set) {
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
        return defensiveCopy(segments);
    }

    private static class LazyLineSegment implements Comparable<LazyLineSegment> {
        private Point min, max;

        LazyLineSegment(Point... points) {
            SortedSet<Point> set = new TreeSet<>(Arrays.asList(points));
            min = set.first();
            max = set.last();
        }

        LineSegment create() {
            return new LineSegment(min, max);
        }

        @Override
        public int compareTo(LazyLineSegment that) {
            int slopesDelta = Double.compare(this.slope(), that.slope());
            if (slopesDelta != 0) return slopesDelta;
            if (isTheSameLine(that)) return 0;
            return this.min.compareTo(that.min);
        }

        private boolean isTheSameLine(LazyLineSegment that) {
            return isOnThisLine(that.min) && isOnThisLine(that.max);
        }

        private boolean isOnThisLine(Point p) {
            double slopeMinToP = min.slopeTo(p);
            return areEqual(slopeMinToP, slope())
                    || slopeMinToP == Double.NEGATIVE_INFINITY;
        }

        private double slope() {
            return min.slopeTo(max);
        }
    }

    private static boolean areEqual(double first, double second) {
        return Double.compare(first, second) == 0;
    }

}
