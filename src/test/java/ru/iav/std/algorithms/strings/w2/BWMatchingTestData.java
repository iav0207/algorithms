package ru.iav.std.algorithms.strings.w2;

import org.testng.annotations.DataProvider;

/**
 * Created by takoe on 05.04.17.
 */
public class BWMatchingTestData {

    @DataProvider(name = "samples")
    public static Object[][] samples() {
        return new Object[][] {
                {   new Input("AGGGAA$", "GA"),             new int[]{3}    },
                {   new Input("ATT$AA", "ATA", "A"),        new int[]{2, 3} },
                {   new Input("AT$TCTATG", "TCT", "TATG"),  new int[]{0, 0} }
        };
    }

    static class Input {
        String text;
        String[] patterns;
        private Input(String text, String... patterns) {
            this.text = text;
            this.patterns = patterns;
        }
    }

}
