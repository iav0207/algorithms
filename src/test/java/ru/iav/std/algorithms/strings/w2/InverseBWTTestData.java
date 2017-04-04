package ru.iav.std.algorithms.strings.w2;

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

    private static String bwt(String s) {
        return new BurrowsWheelerTransform().transform(s);
    }

    @DataProvider(name = "occurrence")
    public static Object[][] occurrence() {
        return new Object[][] {
                {new OccInput("AC$A", 0),   1},
                {new OccInput("AC$A", 3),   2}
        };
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
