package ru.iav.std.algorithms.p1.w5.task;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.iav.std.algorithms.p1.w5.task.struct.SetOfPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by takoe on 24.03.17.
 */
public abstract class SetOfPointsTest {

    private Random random = ThreadLocalRandom.current();
    
    private SetOfPoints set;
    
    @BeforeMethod
    public void reset() {
        set = createSet();
    }

    abstract SetOfPoints createSet();

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfInsertArgumentIsNull() {
        set.insert(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfContainsArgumentIsNull() {
        set.contains(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfRangeArgumentIsNull() {
        set.range(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfNearestArgumentIsNull() {
        set.nearest(null);
    }

    @Test
    public void testSize() {
        List<Point2D> points = randomPoints(100);
        points.forEach(set::insert);
        assertEquals(set.size(), 100);
        points.forEach(set::insert);
        assertEquals(set.size(), 200);
    }

    @Test
    public void testFindForHorizontallyDistributedPoints() {
        List<Point2D> points = horizontallyDistributedPoints(100);
        points.forEach(set::insert);
        assertEquals(set.size(), 100);
        points.forEach(p -> assertTrue(set.contains(p)));
    }

    @Test
    public void testNearest() {
        Point2D a = point(0.1, 0.1), b = point(0.9, 0.9);
        set.insert(a);
        set.insert(b);

        assertEquals(set.nearest(point(0.01, 0.01)), a);
        assertEquals(set.nearest(point(0.99, 0.99)), b);

        assertEquals(set.nearest(point(0.1, 0.1)), a);
        assertEquals(set.nearest(point(0.9, 0.9)), b);

        assertEquals(set.nearest(point(0.3, 0.3)), a);
        assertEquals(set.nearest(point(0.7, 0.7)), b);

        assertEquals(set.nearest(point(0.501, 0.501)), b);
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
        inside.forEach(p -> set.insert(p));
        outside.forEach(p -> set.insert(p));

        Iterable<Point2D> result = set.range(rect);
        for (Point2D each : result) {
            assertTrue(inside.contains(each));
            assertFalse(outside.contains(each));
        }
    }

    private List<Point2D> randomPoints(int size) {
        double[] coords = random.doubles(2*size).toArray();
        List<Point2D> points = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            points.add(point(coords[2*i], coords[2*i + 1]));
        }
        return points;
    }

    private List<Point2D> horizontallyDistributedPoints(int size) {
        double y = random.nextDouble();
        return random.doubles(size).boxed()
                .map(x -> point(x, y))
                .collect(Collectors.toList());
    }

    private static Point2D point(double x, double y) {
        return new Point2D(x, y);
    }

}