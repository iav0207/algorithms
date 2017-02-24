package ru.iav.std.algorithms.p1.w3.task;

import org.testng.annotations.DataProvider;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by takoe on 22.02.17.
 */
public class CollinearPointsTestData {

    private static Random random = ThreadLocalRandom.current();

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

    static Point randomPoint() {
        return new Point(randomCoordinate(), randomCoordinate());
    }

    private static int randomCoordinate() {
        int maxPointCoordinate = 32_767;
        return random.nextInt(maxPointCoordinate);
    }

}
