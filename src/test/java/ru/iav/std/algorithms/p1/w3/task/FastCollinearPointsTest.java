package ru.iav.std.algorithms.p1.w3.task;

/**
 * Created by takoe on 22.02.17.
 */
public class FastCollinearPointsTest extends CollinearPointsTest {

    @Override
    void instantiateWith(Point[] input) {
        new FastCollinearPoints(input);
    }

    @Override
    int numberOfSegments(Point[] input) {
        return new FastCollinearPoints(input).numberOfSegments();
    }
}