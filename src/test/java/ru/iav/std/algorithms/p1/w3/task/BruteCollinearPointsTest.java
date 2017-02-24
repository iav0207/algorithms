package ru.iav.std.algorithms.p1.w3.task;

/**
 * Created by takoe on 21.02.17.
 */
public class BruteCollinearPointsTest extends CollinearPointsTest {

    @Override
    void instantiateWith(Point[] input) {
        new BruteCollinearPoints(input);
    }

    @Override
    int numberOfSegments(Point[] input) {
        return new BruteCollinearPoints(input).numberOfSegments();
    }

}