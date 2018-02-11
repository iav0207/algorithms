package ru.iav.std.algorithms.p2.w4.task;

import java.io.File;

class ResUtil {

    static File getResourceAsFile(String fileName) {
        return new File(ResUtil.class.getResource(fileName).getFile());
    }
}
