package ru.iav.std.algorithms.p1.w1;

import org.apache.commons.lang3.time.StopWatch;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by takoe on 12.02.17.
 */
public class UnionFindPerformanceTest {

    private final int N = 100_000;

    private Random random = ThreadLocalRandom.current();

    private StopWatch timer = new StopWatch();

    private UnionFind unionFindBaseImpl = new UnionFindBaseImpl(N);
    private UnionFind quickUnion = new QuickUnion(N);
    private UnionFind weightedQuickUnion = new WeightedQuickUnion(N);
    private UnionFind weightedQuickUnionWithPathCompression = new WeightedQuickUnionWithPathCompression(N);

    private List<UnionFind> ufList = Arrays.asList(
            weightedQuickUnionWithPathCompression,
            weightedQuickUnion,
            quickUnion,
            unionFindBaseImpl);

    private int unionsCount = N;
    private int[] pUnion =  randomArray(unionsCount);
    private int[] qUnion =  randomArray(unionsCount);
    private int[] pFind =   randomArray(N);
    private int[] qFind =   randomArray(N);

    private int[] randomArray(int size) {
        return random.ints(size, 0, N).toArray();
    }

    @Test
    public void testAllUfsPerformance() {
        ufList.forEach(this::testUf);
    }

    private void testUf(UnionFind uf) {
        timer.reset();
        timer.start();

        for (int i = 0; i < unionsCount; i++) {
            uf.union(pUnion[i], qUnion[i]);
        }
        timer.stop();

        long unionTime = timer.getTime();
        timer.reset();

        int trues = 0;

        timer.start();
        for (int i = 0; i < pFind.length; i++) {
            if (uf.connected(pFind[i], qFind[i]))
                trues++;
        }
        long findTime = timer.getTime();
        timer.stop();

        double ratio = (double) trues / (double) pFind.length;
        System.out.printf("\n%s:\n\t\tunion %d ms,\t\tfind %d ms,\t\tratio %f\n",
                uf.getClass().getSimpleName(), unionTime, findTime, ratio);
    }

}