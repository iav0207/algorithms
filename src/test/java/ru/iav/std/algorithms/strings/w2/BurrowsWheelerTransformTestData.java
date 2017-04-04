package ru.iav.std.algorithms.strings.w2;

import org.testng.annotations.DataProvider;

/**
 * Created by takoe on 04.04.17.
 */
public class BurrowsWheelerTransformTestData {

    @DataProvider(name = "samples")
    public static Object[][] samples() {
        return new Object[][] {
                {"AA$",         "AA$"},
                {"ACACACAC$",   "CCCC$AAAA"},
                {"AGACATA$",    "ATG$CAAA"},
        };
    }

}
