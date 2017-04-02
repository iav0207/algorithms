package ru.iav.std.algorithms.strings.w1;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 02.04.17.
 */
public class SuffixTreeTest {

    @Test(dataProvider = "samples", dataProviderClass = SuffixTreeTestData.class)
    public void testSamples(String input, List<String> expected) {
        List<String> actual = new SuffixTree().computeSuffixTreeEdges(input);

        Collections.sort(actual);
        Collections.sort(expected);

        System.out.print("\nInput:\t\t" + input);
        System.out.print("\nExpected:\t");
        expected.forEach(e -> System.out.print(e + " "));
        System.out.print("\nActual:\t\t");
        actual.forEach(e -> System.out.print(e + " "));
        System.out.println();
        assertEquals(actual, expected);
    }

}