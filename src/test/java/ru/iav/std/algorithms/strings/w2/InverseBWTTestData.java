package ru.iav.std.algorithms.strings.w2;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

/**
 * Created by takoe on 04.04.17.
 */
public class InverseBWTTestData {

    @DataProvider(name = "samples")
    public static Object[][] samples() {
        return new Object[][] {
                {"AC$A",        "ACA$"},
                {"AGGGAA$",     "GAGAGA$"},
                {"TTCCTAACG$A", "TACATCACGT$"},
                {bwt("ABCDEFG$"), "ABCDEFG$"}
        };
    }

    @DataProvider(name = "largeSet")
    public static Object[][] largeSet() {
        String s = RandomStringUtils.randomAlphabetic(10_000) + "$";
        return new Object[][] {{ bwt(s), s}};
    }

    private static String bwt(String s) {
        return new BurrowsWheelerTransform().transform(s);
    }

    static class OccInput {
        String s;
        int i;
        OccInput(String s, int i) {
            this.s = s;
            this.i = i;
        }
    }

}
