package ru.iav.std.algorithms.p1.w3.task;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 21.02.17.
 */
public class BruteCollinearPointsTest {

    private static Random random = ThreadLocalRandom.current();

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfConstructorArgumentIsNull() {
        new BruteCollinearPoints(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfConstructorArgumentContainsNull() {
        new BruteCollinearPoints(new Point[] {randomPoint(), randomPoint(), null, randomPoint()});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfConstructorArgumentContainsRepeatedPoint() {
        Point[] points = new Point[] {randomPoint(), new Point(3, 6), randomPoint(), randomPoint(), new Point(3, 6)};
        new BruteCollinearPoints(points);
    }

    @Test
    public void shouldConstructorBeSilentIfEverythingIsOk() {
        new BruteCollinearPoints(new Point[] {randomPoint()});
    }

    @Test(dataProvider = "inputs")
    public void testSegmentsNum(Point[] given, int expectedSegmentsNum) {
        assertEquals(new BruteCollinearPoints(given).numberOfSegments(), expectedSegmentsNum);
    }

    @DataProvider(name = "inputs")
    public static Object[][] inputs() {
        return new Object[][] {
                {new Point[]{
                        new Point(19000, 10000),
                        new Point(18000, 10000),
                        new Point(32000, 10000),
                        new Point(21000, 10000),
                        new Point(1234, 5678),
                        new Point(14000, 10000),
                }, 1},
                {new Point[]{
                        new Point(10000, 0),
                        new Point(0, 10000),
                        new Point(3000, 7000),
                        new Point(7000, 3000),
                        new Point(20000, 21000),
                        new Point(3000, 4000),
                        new Point(14000, 15000),
                        new Point(6000, 7000),
                }, 2},
        };
    }

    private static Point randomPoint() {
        return new Point(randomCoordinate(), randomCoordinate());
    }

    private static int randomCoordinate() {
        int maxPointCoordinate = 32_767;
        return random.nextInt(maxPointCoordinate);
    }

}