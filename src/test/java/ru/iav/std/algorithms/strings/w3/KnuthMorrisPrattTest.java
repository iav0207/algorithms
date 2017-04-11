package ru.iav.std.algorithms.strings.w3;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 11.04.17.
 */
public class KnuthMorrisPrattTest {

    @Test(dataProvider = "samples", dataProviderClass = KnuthMorrisPrattTestData.class)
    public void testSamples(Pair<String, String> input, List<Integer> expected) {
        List<Integer> actual = new KnuthMorrisPratt().findPattern(input.getLeft(), input.getRight());
        assertEquals(actual, expected);
    }

}