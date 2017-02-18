package ru.iav.std.algorithms.p1.w2.task;

import edu.princeton.cs.algs4.In;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created by takoe on 19.02.17.
 */
public class PermutationTest {

    @Test
    public void test() {
        URL resource = Permutation.class.getResource("tale.txt");
        In in = new In(resource);       // input file

        new Permutation(100, in.readAll()).printStringsUniformlyAtRandom();
    }

}