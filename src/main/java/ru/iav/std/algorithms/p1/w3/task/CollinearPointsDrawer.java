package ru.iav.std.algorithms.p1.w3.task;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.net.URL;
import java.util.Objects;

/**
 * Created by takoe on 21.02.17.
 */
public class CollinearPointsDrawer {

    public static void main(String[] args) {

        // read the n points from a file
        String inputFileName = args[0];
        Objects.requireNonNull(inputFileName);
        URL resource = CollinearPointsDrawer.class.getResource(inputFileName);
        In in = new In(resource);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
