package ru.iav.std.algorithms.p2.w2.task;

import java.io.File;

class ResourceUtil {

    static File getResourceAsFile(String fileName) {
        return new File(ResourceUtil.class.getResource(fileName).getFile());
    }
}
