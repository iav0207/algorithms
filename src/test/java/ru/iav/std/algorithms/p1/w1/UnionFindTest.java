package ru.iav.std.algorithms.p1.w1;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.testng.Assert.assertEquals;

/**
 * Created by takoe on 12.02.17.
 */
public class UnionFindTest {

    private final int N = 10;

    private UnionFind unionFindBaseImpl = new UnionFindBaseImpl(N);
    private UnionFind quickUnion = new QuickUnion(N);
    private UnionFind weightedQuickUnion = new WeightedQuickUnion(N);
    private UnionFind weightedQuickUnionWithPathCompression = new WeightedQuickUnionWithPathCompression(N);

    @BeforeClass
    public void init() {
        for (UnionFind each : Arrays.asList(
                unionFindBaseImpl,
                quickUnion,
                weightedQuickUnion,
                weightedQuickUnionWithPathCompression)) {
            each.union(4, 3);
            each.union(3, 8);
            each.union(6, 5);
            each.union(9, 4);
            each.union(2, 1);
            each.union(5, 0);
        }
    }

    @DataProvider(name = "unionFindTestData")
    public static Object[][] unionFindTestData() {
        return new Object[][] {
                {Pair.of(8, 9),     TRUE},
                {Pair.of(5, 0),     TRUE},
                {Pair.of(0, 6),     TRUE},
                {Pair.of(2, 1),     TRUE},
                {Pair.of(8, 4),     TRUE},

                {Pair.of(0, 1),     FALSE},
                {Pair.of(7, 8),     FALSE},
                {Pair.of(7, 2),     FALSE},
                {Pair.of(6, 1),     FALSE},
                {Pair.of(5, 4),     FALSE},
        };
    }

    @Test(dataProvider = "unionFindTestData")
    public void testBase(Pair<Integer, Integer> given, boolean expected) {
            assertEquals(unionFindBaseImpl.connected(given.getLeft(), given.getRight()), expected);
    }

    @Test(dataProvider = "unionFindTestData")
    public void testQU(Pair<Integer, Integer> given, boolean expected) {
            assertEquals(quickUnion.connected(given.getLeft(), given.getRight()), expected);
    }

    @Test(dataProvider = "unionFindTestData")
    public void testWQU(Pair<Integer, Integer> given, boolean expected) {
            assertEquals(weightedQuickUnion.connected(given.getLeft(), given.getRight()), expected);
    }

    @Test(dataProvider = "unionFindTestData")
    public void testWQUPC(Pair<Integer, Integer> given, boolean expected) {
            assertEquals(weightedQuickUnionWithPathCompression.connected(given.getLeft(), given.getRight()), expected);
    }

}