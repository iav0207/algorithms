package ru.iav.std.algorithms.p1.w5.task;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by takoe on 24.03.17.
 */
public class PointSETTest {
    
    private PointSET pointSet;
    
    @BeforeMethod
    public void reset() {
        pointSet = new PointSET();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfInsertArgumentIsNull() {
        pointSet.insert(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfContainsArgumentIsNull() {
        pointSet.contains(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfRangeArgumentIsNull() {
        pointSet.range(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfNearestArgumentIsNull() {
        pointSet.nearest(null);
    }

    @Test
    public void testNearest() {
        Point2D a = point(0.1, 0.1), b = point(0.9, 0.9);
        pointSet.insert(a);
        pointSet.insert(b);

        assertEquals(pointSet.nearest(point(0.01, 0.01)), a);
        assertEquals(pointSet.nearest(point(0.99, 0.99)), b);

        assertEquals(pointSet.nearest(point(0.1, 0.1)), a);
        assertEquals(pointSet.nearest(point(0.9, 0.9)), b);

        assertEquals(pointSet.nearest(point(0.3, 0.3)), a);
        assertEquals(pointSet.nearest(point(0.7, 0.7)), b);

        assertEquals(pointSet.nearest(point(0.5, 0.5)), b);
    }

    @Test
    public void testContains() {
        RectHV rect = new RectHV(0.3, 0.3, 0.7, 0.7);
        List<Point2D> inside = Arrays.asList(
                point(0.3, 0.3),
                point(0.3, 0.7),
                point(0.5, 0.6),
                point(0.7, 0.7),
                point(0.7, 0.3)
        );
        List<Point2D> outside = Arrays.asList(
                point(0.3, 0.1),
                point(0.3, 0.8),
                point(0.9, 0.5),
                point(0.7, 0.71),
                point(0.71, 0.3)
        );
        inside.forEach(p -> pointSet.insert(p));
        outside.forEach(p -> pointSet.insert(p));

        Iterable<Point2D> result = pointSet.range(rect);
        for (Point2D each : result) {
            assertTrue(inside.contains(each));
            assertFalse(outside.contains(each));
        }
    }

    private static Point2D point(double x, double y) {
        return new Point2D(x, y);
    }

}