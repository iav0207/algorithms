package ru.iav.std.algorithms.p2.w5.task;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class IOUtil {

    static void resetSystemOutToConsole() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    static InputStream getResourceAsStream(String filename) {
        return IOUtil.class.getResourceAsStream(filename);
    }
}
