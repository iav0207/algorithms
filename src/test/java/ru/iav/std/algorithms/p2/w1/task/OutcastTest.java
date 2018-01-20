package ru.iav.std.algorithms.p2.w1.task;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OutcastTest {

    private final Outcast outcast = new Outcast(new WordNet("synsets.txt", "hypernyms.txt"));

    @Test(dataProvider = "outcastSamples")
    public void testOutcast(String[] input, String expected) {
        assertEquals(outcast.outcast(input), expected);
    }

    @DataProvider(name = "outcastSamples")
    public static Object[][] outcastSamples() {
        return new Object[][] {
                {arr("horse zebra cat bear table"),
                        "table"},
                {arr("water soda bed orange_juice milk apple_juice tea coffee"),
                        "bed"},
                {arr("apple pear peach banana lime lemon blueberry strawberry mango watermelon potato"),
                        "potato"},
        };
    }

    private static String[] arr(String nouns) {
        return nouns.split("\\s+");
    }

}
