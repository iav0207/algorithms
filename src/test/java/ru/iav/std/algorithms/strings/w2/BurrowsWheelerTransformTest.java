package ru.iav.std.algorithms.strings.w2;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 04.04.17.
 */
public class BurrowsWheelerTransformTest {

    @Test(dataProvider = "samples", dataProviderClass = BurrowsWheelerTransformTestData.class)
    public void testSamples(String input, String expected) {
        assertEquals(new BurrowsWheelerTransform().transform(input), expected);
    }

}