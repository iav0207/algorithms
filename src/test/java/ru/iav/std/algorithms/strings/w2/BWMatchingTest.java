package ru.iav.std.algorithms.strings.w2;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.internal.collections.Ints.asList;

/**
 * Created by takoe on 05.04.17.
 */
public class BWMatchingTest {   // FIXME: 11.04.17

    @Test(dataProvider = "samples", dataProviderClass = BWMatchingTestData.class)
    public void testSamples(BWMatchingTestData.Input input, int[] expected) {
        assertEquals(asList(BWMatching.countOccurrences(input.text, input.patterns)), asList(expected));
    }

}