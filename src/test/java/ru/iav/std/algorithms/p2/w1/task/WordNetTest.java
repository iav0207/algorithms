package ru.iav.std.algorithms.p2.w1.task;

import java.util.Collection;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WordNetTest {

    private final WordNet wn = new WordNet("synsets.txt", "hypernyms.txt");

    @Test(expectedExceptions = IllegalArgumentException.class,
            dataProvider = "nullConstructorArgs")
    public void shouldConstructorThrowIaeIfArgIsNull(String synsets, String hypernyms) throws Exception {
        new WordNet(synsets, hypernyms);
    }

    @DataProvider(name = "nullConstructorArgs")
    public static Object[][] nullConstructorArgs() {
        return new Object[][] {
                {null, "abc"},
                {"abc", null},
                {null, null}
        };
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldConstructorThrowIaeIfInputGraphIsNotDAG() throws Exception {
        // TODO implement
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldDistanceThrowIaeIfNounIsNotInWordNet() throws Exception {
        wn.distance("qerg", "bird");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldSapThrowIaeIfNounIsNotInWordNet() throws Exception {
        wn.sap("qerg", "bird");
    }

    @Test
    public void sap_worm_bird() {
        assertEquals(wn.sap("worm", "bird"),
                "animal animate_being beast brute creature fauna");
    }

    @Test
    public void distance_worm_bird() {
        assertEquals(wn.distance("worm", "bird"), 5);
    }

    @Test(dataProvider = "farNouns")
    public void distance_farNouns(String nounA, String nounB, int expected) {
        assertEquals(wn.distance(nounA, nounB), expected);
    }

    @DataProvider(name = "farNouns")
    public static Object[][] farNouns() {
        return new Object[][] {
                {"white_marlin", "mileage", 23},
                {"Black_Plague", "black_marlin", 33},
                {"American_water_spaniel", "histology", 27},
                {"Brown_Swiss", "barrel_roll", 29},
        };
    }

    @Test
    public void nounsTotal() {
        assertEquals(((Collection<String>) wn.nouns()).size(), 119_188);
    }

}
