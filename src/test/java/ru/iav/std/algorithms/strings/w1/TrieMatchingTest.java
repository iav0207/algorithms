package ru.iav.std.algorithms.strings.w1;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 02.04.17.
 */
public class TrieMatchingTest {

    @Test(dataProvider = "samples", dataProviderClass = TrieMatchingTestData.class)
    public void testSamples(Pair<String, List<String>> input, List<Integer> expected) {
        assertEquals(new TrieMatching().solve(input.getLeft(), input.getRight()), expected);
    }

}