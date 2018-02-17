package ru.iav.std.algorithms.p2.w5.task;

import java.util.stream.IntStream;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CircularSuffixArrayTest {

    @Test
    public void sampleTest() {
        String text = "ABRACADABRA!";
        CircularSuffixArray csa = new CircularSuffixArray(text);

        int[] expected = new int[] {11, 10, 7, 0, 3, 5, 8, 1, 4, 6, 9, 2};
        int[] actual = new int[text.length()];
        IntStream.range(0, text.length()).forEach(i -> actual[i] = csa.index(i));

        assertEquals(actual, expected);
    }

    @Test
    public void testMain() {
        CircularSuffixArray.main("ABRACADABRA!");
    }

}
