package ru.iav.std.algorithms.p2.w3.task;

import java.io.File;

import edu.princeton.cs.algs4.StdOut;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.testng.Assert.assertEquals;

public class BaseballEliminationTest {

    @Test(dataProvider = "eliminated")
    public void testEliminated(String resourceName, String team, boolean expected) {
        assertEquals(baseballElimination(resourceName).isEliminated(team), expected);
    }

    @Test(dataProvider = "certificate")
    public void testCertificate(String resourceName, String team, Iterable<String> expected) {
        assertEquals(baseballElimination(resourceName).certificateOfElimination(team), expected);
    }

    @Test(dataProvider = "files")
    public void runResultPrinting(String resourceName) {
        BaseballElimination division = baseballElimination(resourceName);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }

    @DataProvider(name = "eliminated")
    public static Object[][] eliminated() {
        return new Object[][] {
                {"teams4.txt",  "Atlanta",          false   },
                {"teams4.txt",  "Philadelphia",     true    },
                {"teams4.txt",  "New_York",         false   },
                {"teams4.txt",  "Montreal",         true    },

                {"teams5.txt",  "New_York",         false   },
                {"teams5.txt",  "Baltimore",        false   },
                {"teams5.txt",  "Boston",           false   },
                {"teams5.txt",  "Toronto",          false   },
                {"teams5.txt",  "Detroit",          true    },
        };
    }
    
    @DataProvider(name = "certificate")
    public static Object[][] certificate() {
        return new Object[][] {
                {"teams4.txt",  "Atlanta",          null    },
                {"teams4.txt",  "Philadelphia",     asList("Atlanta", "New_York")},
                {"teams4.txt",  "New_York",         null    },
                {"teams4.txt",  "Montreal",         singletonList("Atlanta")},

                {"teams5.txt",  "New_York",         null    },
                {"teams5.txt",  "Baltimore",        null    },
                {"teams5.txt",  "Boston",           null    },
                {"teams5.txt",  "Toronto",          null    },
                {"teams5.txt",  "Detroit",          asList("New_York", "Baltimore", "Boston", "Toronto")},
        };
    }

    @DataProvider(name = "files")
    public static Object[][] files() {
        return new Object[][] {{"teams4.txt"}, {"teams5.txt"}};
    }

    private static BaseballElimination baseballElimination(String resourceName) {
        return new BaseballElimination(getResourceAsFile(resourceName).getAbsolutePath());
    }

    private static File getResourceAsFile(String fileName) {
        return new File(BaseballElimination.class.getResource(fileName).getFile());
    }

}
