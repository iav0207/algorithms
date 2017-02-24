package ru.iav.std.algorithms.p1.w2.sort;

import org.testng.annotations.DataProvider;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by takoe on 20.02.17.
 */
public class SortTestData {

    private static Random random = ThreadLocalRandom.current();

    private static final int items = 10_000;

    @DataProvider(name = "random")
    public static Object[][] random() {
        return new Object[][]{
                {
                        random.ints(items)
//                                .map(i -> i % 1000)
                                .boxed()
                                .collect(Collectors.toList()),
                        null
                }
        };
    }

    @DataProvider(name = "sorted")
    public static Object[][] sorted() {
        return new Object[][] {
                {
                        IntStream.range(0, items)
                                .boxed()
                                .collect(Collectors.toList()),
                        null
                }
        };
    }

}
