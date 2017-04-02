package ru.iav.std.algorithms.strings.w1;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by takoe on 02.04.17.
 */
public class TrieMatchingTestData {

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
                        input("AATCGGGTTCAATCGGGGT\n" +
                                "ATCG\n" +
                                "GGGT"),
                        Arrays.asList(1, 4, 11, 15)
                }
        };
    }

    private static Pair<String, List<String>> input(String s) {
        String[] ss = s.split("\\s+");
        String text = ss[0];
        List<String> patterns = new ArrayList<>();
        patterns.addAll(Arrays.asList(ss).subList(1, ss.length));
        return Pair.of(text, patterns);
    }

}
