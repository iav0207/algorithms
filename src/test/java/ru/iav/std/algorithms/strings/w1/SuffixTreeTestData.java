package ru.iav.std.algorithms.strings.w1;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by takoe on 02.04.17.
 */
public class SuffixTreeTestData {

    @DataProvider(name = "samples")
    public static Object[][] samples() {
        return new Object[][] {

                {   "A$",
                        asList("$", "A$")                       },

                {   "ACA$",
                        asList("$", "A", "$", "CA$", "CA$")     },

                {   "ATAAATG$",
                        asList("AAATG$", "G$", "T", "ATG$",
                                "TG$", "A", "A", "AAATG$",
                                "G$", "T", "G$", "$")           },

                {   "AAA$",
                        asList("$", "$", "$", "A", "A", "A$")   },

                {   "AAAAAAAAAA$",
                        listOfAs(10)},

                {   "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA$",
                        listOfAs(88)                            }

        };
    }

    private static List<String> listOfAs(int numberOfA) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < numberOfA; i++) {
            if (i > 0) list.add("A");
            list.add("$");
        }
        list.add("A$");
        return list;
    }

}
