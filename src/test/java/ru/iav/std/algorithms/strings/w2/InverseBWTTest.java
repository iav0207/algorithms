package ru.iav.std.algorithms.strings.w2;

import org.testng.annotations.Test;
import ru.iav.std.algorithms.util.PerformanceTester;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 04.04.17.
 */
public class InverseBWTTest {

    @Test(dataProvider = "samples", dataProviderClass = InverseBWTTestData.class)
    public void testSamples(String input, String expected) {
        assertEquals(new InverseBWT().inverseBWT(input), expected);
    }

    @Test(dataProvider = "largeSet", dataProviderClass = InverseBWTTestData.class)
    public void testLargeSet(String input, String expected) {
        PerformanceTester.printExecutionTime(() -> new InverseBWT().inverseBWT(input));
//        assertEquals(new InverseBWT().inverseBWT(input), expected);
    }

}