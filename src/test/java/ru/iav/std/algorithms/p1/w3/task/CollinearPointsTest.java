package ru.iav.std.algorithms.p1.w3.task;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static ru.iav.std.algorithms.p1.w3.task.CollinearPointsTestData.randomPoint;

/**
 * Created by takoe on 24.02.17.
 */
public abstract class CollinearPointsTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNpeIfConstructorArgumentIsNull() {
        instantiateWith(null);
    }

    @Test(expectedExceptions = NullPointerException.class,
            dataProviderClass = CollinearPointsTestData.class, dataProvider = "containsNull")
    public void shouldThrowNpeIfConstructorArgumentContainsNull(Point[] input, Object ignore) {
        instantiateWith(input);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            dataProviderClass = CollinearPointsTestData.class, dataProvider = "containsDuplicate")
    public void shouldThrowIllegalArgumentExceptionIfConstructorArgumentContainsRepeatedPoint(Point[] input,
                                                                                              Object ignore) {
        instantiateWith(input);
    }

    @Test
    public void shouldConstructorBeSilentIfEverythingIsOk() {
        instantiateWith(new Point[] {randomPoint()});
    }

    @Test(dataProvider = "inputs", dataProviderClass = CollinearPointsTestData.class)
    public void testSegmentsNum(Point[] given, int expectedSegmentsNum) {
        assertEquals(numberOfSegments(given), expectedSegmentsNum);
    }

    abstract void instantiateWith(Point[] input);

    abstract int numberOfSegments(Point[] input);

}
