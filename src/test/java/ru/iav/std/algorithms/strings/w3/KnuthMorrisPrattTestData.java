package ru.iav.std.algorithms.strings.w3;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.DataProvider;

import java.util.Collections;

import static org.testng.internal.collections.Ints.asList;

/**
 * Created by takoe on 11.04.17.
 */
public class KnuthMorrisPrattTestData {

    @DataProvider(name = "samples")
    public static Object[][] samples() {
        return new Object[][] {
                {   Pair.of("TACG", "GT"),                  Collections.EMPTY_LIST  },
                {   Pair.of("ATA", "ATATA"),                asList(0, 2)            },
                {   Pair.of("ATAT", "GATATATGCATATACTT"),   asList(1, 3, 9)         }
        };
    }

}
