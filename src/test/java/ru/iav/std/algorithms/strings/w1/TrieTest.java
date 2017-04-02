package ru.iav.std.algorithms.strings.w1;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 02.04.17.
 */
public class TrieTest {

    @Test(dataProvider = "samples", dataProviderClass = TrieTestData.class)
    public void testSamples(String input, String expected) {
        assertEquals(new Trie().run(input), expected);
    }

}