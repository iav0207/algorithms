package ru.iav.std.algorithms.strings.w2;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 04.04.17.
 */
public class InverseBWTTest {

    @Test(dataProvider = "samples", dataProviderClass = InverseBWTTestData.class)
    public void testSamples(String input, String expected) {
        assertEquals(new InverseBWT().inverseBWT(input), expected);
    }

    @Test(dataProvider = "occurrence", dataProviderClass = InverseBWTTestData.class)
    public void testGetOccurrence(InverseBWTTestData.OccInput input, int expected) {
        assertEquals(new InverseBWT().getOccurrenceNumber(input.s, input.i), expected);
    }

}