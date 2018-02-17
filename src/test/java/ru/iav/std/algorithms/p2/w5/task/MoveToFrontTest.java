package ru.iav.std.algorithms.p2.w5.task;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MoveToFrontTest {

    @Test
    public void abra() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setIn(getResourceAsStream("abra.txt"));
        System.setOut(new PrintStream(out));

        MoveToFront.encode();

        byte[] actual = out.toByteArray();
        byte[] expected = IOUtils.toByteArray(getResourceAsStream("abra.txt.mtf"));

        resetSystemOutToConsole();
        System.out.println("Actual: " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(expected));
        assertEquals(actual, expected);
    }

    @Test
    public void abra_symmetry() throws Exception {
        Supplier<InputStream> input = () -> getResourceAsStream("abra.txt");

        byte[] expected = IOUtils.toByteArray(input.get());

        System.setIn(input.get());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        MoveToFront.encode();

        System.setIn(new ByteArrayInputStream(out.toByteArray()));
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        MoveToFront.decode();

        byte[] actual = out.toByteArray();

        resetSystemOutToConsole();
        System.out.println("Actual: " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(expected));
        assertEquals(actual, expected);
    }

    @Test
    public void testArrayRotation() {
        int[] arr = IntStream.range(0, 10).toArray();
        int idxToMove = 7;
        int toMove = arr[idxToMove];

        System.arraycopy(arr, 0, arr, 1, idxToMove);
        arr[0] = toMove;
        assertEquals(arr, new int[] {7, 0, 1, 2, 3, 4, 5, 6, 8, 9});
    }

    private static void resetSystemOutToConsole() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    private static InputStream getResourceAsStream(String filename) {
        return MoveToFrontTest.class.getResourceAsStream(filename);
    }

}
