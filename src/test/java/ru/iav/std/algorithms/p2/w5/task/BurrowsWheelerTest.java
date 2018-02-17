package ru.iav.std.algorithms.p2.w5.task;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static ru.iav.std.algorithms.p2.w5.task.IOUtil.getResourceAsStream;
import static ru.iav.std.algorithms.p2.w5.task.IOUtil.resetSystemOutToConsole;

public class BurrowsWheelerTest {

    @Test
    public void testTransform() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setIn(getResourceAsStream("abra.txt"));
        System.setOut(new PrintStream(out));

        BurrowsWheeler.transform();

        byte[] actual = out.toByteArray();
        byte[] expected = IOUtils.toByteArray(getResourceAsStream("abra.txt.bwt"));

        resetSystemOutToConsole();
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Actual:   " + Arrays.toString(actual));
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "strings")
    public void testSymmetry(String text) throws Exception {
        System.setIn(new ByteArrayInputStream(text.getBytes()));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BurrowsWheeler.transform();

        System.setIn(new ByteArrayInputStream(out.toByteArray()));
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BurrowsWheeler.inverseTransform();

        byte[] actual = out.toByteArray();

        resetSystemOutToConsole();
        System.out.println("Expected: " + Arrays.toString(text.getBytes()));
        System.out.println("Actual:   " + Arrays.toString(actual));
        assertEquals(new String(actual), text);
    }

    @DataProvider(name = "strings")
    public static Object[][] strings() {
        return new Object[][] {
                {"ABRACADABRA!"},
                {"Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_131"},
                {"The goal of the Burrows–Wheeler transform is not to compress a message, but rather to transform"
                        + " it into a form that is more amenable to compression."},
                {""},
                {"$"},
                {" "},
                {"Русский текст"},
        };
    }

}
