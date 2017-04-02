package ru.iav.std.algorithms.strings.w1;

import org.testng.annotations.DataProvider;

/**
 * Created by takoe on 02.04.17.
 */
public class TrieTestData {

    @DataProvider(name = "samples")
    public static Object[][] sample() {
        return new Object[][]{
                {
                        "ATA",
                        "0->1:A\n" +
                                "1->2:T\n" +
                                "2->3:A"
                },
                {
                        "AT\n" +
                                "AG\n" +
                                "AC",
                        "0->1:A\n" +
                                "1->4:C\n" +
                                "1->3:G\n" +
                                "1->2:T"
                },
                {
                        "ATAGA\n" +
                                "ATC\n" +
                                "GAT",
                        "0->1:A\n" +
                                "0->7:G\n" +
                                "1->2:T\n" +
                                "2->3:A\n" +
                                "2->6:C\n" +
                                "3->4:G\n" +
                                "4->5:A\n" +
                                "7->8:A\n" +
                                "8->9:T"
                }
        };
    }

}
