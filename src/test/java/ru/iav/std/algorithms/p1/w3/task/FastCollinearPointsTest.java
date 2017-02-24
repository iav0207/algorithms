package ru.iav.std.algorithms.p1.w3.task;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static ru.iav.std.algorithms.p1.w3.task.CollinearPointsTestData.randomPoint;

/**
 * Created by takoe on 22.02.17.
 */
public class FastCollinearPointsTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfConstructorArgumentIsNull() {
        new FastCollinearPoints(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfConstructorArgumentContainsNull() {
        new FastCollinearPoints(new Point[] {randomPoint(), randomPoint(), null, randomPoint()});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfConstructorArgumentContainsRepeatedPoint() {
        Point[] points = new Point[] {randomPoint(), new Point(3, 6), randomPoint(), randomPoint(), new Point(3, 6)};
        new FastCollinearPoints(points);
    }

    @Test
    public void shouldConstructorBeSilentIfEverythingIsOk() {
        new FastCollinearPoints(new Point[] {randomPoint()});
    }

    @Test(dataProvider = "inputs", dataProviderClass = CollinearPointsTestData.class)
    public void testSegmentsNum(Point[] given, int expectedSegmentsNum) {
        assertEquals(new FastCollinearPoints(given).numberOfSegments(), expectedSegmentsNum);
    }

}