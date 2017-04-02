package ru.iav.std.algorithms.strings.w1;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Collections;

import static ru.iav.std.algorithms.strings.w1.TrieMatchingTestData.input;

/**
 * Created by takoe on 02.04.17.
 */
public class TrieMatchingExtendedTestData {

    @DataProvider(name = "samples")
    public static Object[][] samples() {
        return new Object[][]{
                {
                        input("AAA\n" +
                                "AA"),
                        Arrays.asList(0, 1)
                },
                {
                        input("AA\n" +
                                "T"),
                        Collections.<Integer>emptyList()
                },
                {
                        input("ACATA\n" +
                                "AT\n" +
                                "A\n" +
                                "AG"),
                        Arrays.asList(0, 2, 4)
                }
        };
    }

}
