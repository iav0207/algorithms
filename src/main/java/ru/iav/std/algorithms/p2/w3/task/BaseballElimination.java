package ru.iav.std.algorithms.p2.w3.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

public class BaseballElimination {

    private final Map<String, Integer> index = new HashMap<>();
    private final String[] names;
    private final int[] wi, lo, re;
    private final int[][] g;

    // some precomputed constants for the algorithm
    private final int n;
    private final int v;
    private final int source;
    private final int sink;

    /**
     * create a baseball division from given filename in format specified below
     */
    public BaseballElimination(String filename) {
        In in = new In(filename);
        int teamsNum = in.readInt();
        names = new String[teamsNum];
        wi = new int[teamsNum];
        lo = new int[teamsNum];
        re = new int[teamsNum];
        g = new int[teamsNum][teamsNum];
        for (int i = 0; i < teamsNum; i++) {
            names[i] = in.readString();
            index.put(names[i], i);
            wi[i] = in.readInt();
            lo[i] = in.readInt();
            re[i] = in.readInt();
            for (int j = 0; j < teamsNum; j++) {
                g[i][j] = in.readInt();
            }
        }

        n = names.length;
        int gamesNum = n * (n - 1) / 2;
        v = n + gamesNum + 2;   // teams + games + source + sink
        source = v - 2;
        sink = v - 1;
    }

    /**
     * number of teams
     */
    public int numberOfTeams() {
        return n;
    }

    /**
     * all teams
     */
    public Iterable<String> teams() {
        return Arrays.asList(names);
    }

    /**
     * number of wins for given team
     */
    public int wins(String team) {
        return wi[idx(team)];
    }

    /**
     * number of losses for given team
     */
    public int losses(String team) {
        return lo[idx(team)];
    }

    /**
     * number of remaining games for given team
     */
    public int remaining(String team) {
        return re[idx(team)];
    }

    /**
     * number of remaining games between team1 and team2
     */
    public int against(String team1, String team2) {
        return g[idx(team1)][idx(team2)];
    }

    /**
     * is given team eliminated?
     */
    public boolean isEliminated(String team) {
        if (!eliminateTrivially(team).isEmpty()) {
            return true;
        }
        final int x = idx(team);
        FlowNetwork network = buildFlowNetwork(x);

        // fill the flow network with max flow
        new FordFulkerson(network, source, sink);

        for (FlowEdge edge : network.adj(source)) {
            if (edge.residualCapacityTo(edge.other(source)) > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * subset R of teams that eliminates given team; null if not eliminated
     */
    public Iterable<String> certificateOfElimination(String team) {
        if (!isEliminated(team)) {
            return null;
        }

        Collection<String> teamsEliminatingXTrivially = eliminateTrivially(team);
        if (!teamsEliminatingXTrivially.isEmpty()) {
            return teamsEliminatingXTrivially;
        }

        final int x = idx(team);
        FlowNetwork network = buildFlowNetwork(x);

        // fill the flow network with max flow
        FordFulkerson ff = new FordFulkerson(network, source, sink);

        final ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (ff.inCut(i)) {
                result.add(names[i]);
            }
        }
        return result;
    }

    /**
     * @return Teams that eliminate the given team, if the team is trivially eliminated,
     * otherwise – empty collection;
     */
    private Collection<String> eliminateTrivially(String team) {
        int maxPossibleWinsX = getMaxPossibleWins(idx(team));
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (maxPossibleWinsX < wi[i]) {
                result.add(names[i]);
            }
        }
        return result;
    }

    private FlowNetwork buildFlowNetwork(int x) {
        final int maxPossibleWinsX = getMaxPossibleWins(x);
        final FlowNetwork network = new FlowNetwork(v);

        int gameIdx = n;

        for (int i = 0; i < n; i++) {
            if (i == x) continue;

            network.addEdge(new FlowEdge(i, sink, Math.max(0, maxPossibleWinsX - wi[i])));
            for (int j = i + 1; j < n; j++) {
                if (j == x) continue;
                gameIdx++;
                network.addEdge(new FlowEdge(source, gameIdx, g[i][j]));
                network.addEdge(new FlowEdge(gameIdx, i, Double.POSITIVE_INFINITY));
                network.addEdge(new FlowEdge(gameIdx, j, Double.POSITIVE_INFINITY));
            }
        }

        return network;
    }

    private int getMaxPossibleWins(int x) {
        return wi[x] + re[x];
    }

    private int idx(String team) {
        if (!index.containsKey(team)) {
            throw new IllegalArgumentException("No such team: " + team);
        }
        return index.get(team);
    }

}
