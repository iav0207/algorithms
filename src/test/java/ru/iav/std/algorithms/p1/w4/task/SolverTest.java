package ru.iav.std.algorithms.p1.w4.task;

import org.testng.annotations.Test;

/**
 * Created by takoe on 01.03.17.
 */
public class SolverTest {

    @Test
    public void test() {
        String inputFileName = "puzzle10";
        Solver.main(new String[]{inputFileName + ".txt"});
    }

}