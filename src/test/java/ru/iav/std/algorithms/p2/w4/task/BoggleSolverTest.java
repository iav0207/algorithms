package ru.iav.std.algorithms.p2.w4.task;

import edu.princeton.cs.algs4.In;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ru.iav.std.algorithms.p2.w4.task.ResUtil.getResourceAsFile;

public class BoggleSolverTest {

    private BoggleSolver solver;

    @BeforeClass
    public void init() {
        String[] dictionary = new In(getResourceAsFile("dictionary-yawl.txt")).readAllStrings();
        solver = new BoggleSolver(dictionary);
    }

    @Test(dataProvider = "boards")
    public void test(String boardFileName) throws Exception {
        BoggleBoard board = new BoggleBoard(getResourceAsFile(boardFileName).getAbsolutePath());
        int c = 0;
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            System.out.println(word);
            score += solver.scoreOf(word);
            c++;
        }
        System.out.printf("Total words found: %d.%nScore: %d.%n", c, score);
    }

    @DataProvider(name = "boards")
    public static Object[][] boards() {
        return new Object[][] {
                {"board4x4.txt"},
                {"board-q.txt"},
                {"board-points4.txt"},
                {"board-aqua.txt"},
                {"board-points100.txt"},
                {"board-points300.txt"},
                {"board-points26539.txt"},
                {"board-pneumonoultramicroscopicsilicovolcanoconiosis.txt"},
                {"board-antidisestablishmentarianisms.txt"},
                {"board-rotavator.txt"},
        };
    }
}
