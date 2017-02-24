package ru.iav.std.algorithms.p1.w3.task;

import org.testng.annotations.Test;

/**
 * Created by takoe on 21.02.17.
 */
public class CollinearPointsDrawerTest {

    @Test
    public void test() {
        String inputFileName = "input48.txt";

        run(inputFileName);
    }

    private static void run(String inputFileName) {
        CollinearPointsDrawer.main(new String[] {inputFileName});
    }

}