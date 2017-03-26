package ru.iav.std.algorithms.p1.w5.task;

import edu.princeton.cs.algs4.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by takoe on 26.03.17.
 */
class SetOfPointsTestData {

    private static Random random = ThreadLocalRandom.current();

    static List<Point2D> randomPoints(int size) {
        double[] coords = random.doubles(2*size).toArray();
        List<Point2D> points = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            points.add(point(coords[2*i], coords[2*i + 1]));
        }
        return points;
    }

    static List<Point2D> horizontallyDistributedPoints(int size) {
        double y = random.nextDouble();
        return random.doubles(size).boxed()
                .map(x -> point(x, y))
                .collect(Collectors.toList());
    }

    static Point2D point(double x, double y) {
        return new Point2D(x, y);
    }

}
