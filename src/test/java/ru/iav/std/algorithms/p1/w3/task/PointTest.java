package ru.iav.std.algorithms.p1.w3.task;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.collections.Pair;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 21.02.17.
 */
public class PointTest {

    @Test(dataProvider = "comparison")
    public void testCompareTo(Pair<Point, Point> points, int expected) {
        assertEquals(points.first().compareTo(points.second()), expected);
    }

    @DataProvider(name = "comparison")
    public static Object[][] comparison() {
        return new Object[][] {
                //     x1  y1  x2  y2
                {given( 0,  0,  0,  0),         0   },
                {given( 0,  0,  1,  0),        -1   },
                {given( 0,  0, -5,  1),        -1   },
                {given( 1,  0,  0,  0),         1   },
                {given( 1,  1,  1,  0),         1   }
        };
    }

    @Test(dataProvider = "slope")
    public void testSlopeTo(Pair<Point, Point> points, double expected) {
        assertEquals(points.first().slopeTo(points.second()), expected);
    }

    @DataProvider(name = "slope")
    public static Object[][] slope() {
        return new Object[][] {
                //     x1  y1  x2  y2
                {given( 0,  0,  0,  0),         Double.NEGATIVE_INFINITY},
                {given( 0,  0,  1,  0),         0   },
                {given( 0,  0,  0,  1),         Double.POSITIVE_INFINITY},
                {given( 0,  0, -1,  0),         0   },
                {given( 0,  0,  1,  1),         1   },
                {given( 0,  0,  0, -1),         Double.POSITIVE_INFINITY},
                {given( 0,  0, -1, -1),         1   },
                {given( 0,  0, -1,  1),        -1   },
                {given( 0,  0,  1, -1),        -1   }
        };
    }

    private static Pair<Point, Point> given(int x1, int y1, int x2, int y2) {
        return Pair.of(new Point(x1, y1), new Point(x2, y2));
    }

}