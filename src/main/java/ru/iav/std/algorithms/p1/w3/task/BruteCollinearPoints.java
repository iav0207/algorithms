package ru.iav.std.algorithms.p1.w3.task;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *  Examines 4 points at a time and checks whether they all
 *  lie on the same line segment, returning all such line segments.
 *  To check whether the 4 points p, q, r, and s are collinear,
 *  check whether the three slopes between p and q, between p and r,
 *  and between p and s are all equal.
 */
public class BruteCollinearPoints {

    private LineSegment[] segments = new LineSegment[] {};

    private Point[] points;

    /**
     * Finds all line segments containing 4 points
     */
    public BruteCollinearPoints(Point[] points) {
        validate(points);
        this.points = points;
        segments = findDistinctSegments();
    }

    private LineSegment[] findDistinctSegments() {
        int n = points.length;
        List<LineSegment> segmentList = new LinkedList<>();
        List<Integer> excluded = new LinkedList<>();
        OUTER: for (int p = 0; p < n - 3; p++) {
            for (int q = p + 1; q < n - 2; q++) {
                double pqSlope = slope(p, q);
                for (int r = q + 1; r < n - 1; r++) {
                    double prSlope = slope(p, r);
//                    if (areEqual(pqSlope, prSlope) && !containsAny(excluded, p, q, r)) {
                    for (int s = r + 1; s < n; s++) {
                        double psSlope = slope(p, s);
                        if (areEqual(pqSlope, prSlope, psSlope) && !containsAny(excluded, p, q, r, s)) {
                            segmentList.add(createLineSegment(p, q, r, s));
                            excluded.addAll(Arrays.asList(p, q, r, s));
                            continue OUTER;
                        }
                    }
//                    }
                }
            }
        }
        return segmentList.toArray(new LineSegment[segmentList.size()]);
    }

    private boolean containsAny(List<Integer> list, int... ints) {
        for (int value : ints) {
            if (list.contains(value)) return true;
        }
        return false;
    }

    private double slope(int p, int q) {
        double slope = points[p].slopeTo(points[q]);
        if (slope == Double.NEGATIVE_INFINITY) {
            throw new IllegalArgumentException("There must be no duplicate points.");
        }
        return slope;
    }

    private static boolean areEqual(double slopeOne, double slopeTwo, double... other) {
        boolean equal = Double.compare(slopeOne, slopeTwo) == 0;
        for (double each : other)
            equal &= Double.compare(slopeOne, each) == 0;
        return equal;
    }

    private LineSegment createLineSegment(int... ids) {
        int min = ids[0];
        int max = min;
        for (int i : ids) {
            if (less(i, min)) min = i;
            if (less(max, i)) max = i;
        }
        return new LineSegment(points[min], points[max]);
    }

    private boolean less(int i, int j) {
        return points[i].compareTo(points[j]) < 0;
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

}
