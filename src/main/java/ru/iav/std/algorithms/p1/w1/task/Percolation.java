package ru.iav.std.algorithms.p1.w1.task;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by takoe on 15.02.17.
 */
public class Percolation {

    private final int n;

    private final int topSiteIndex;
    private final int bottomSiteIndex;

    private boolean[] opened;
    private int openSitesCounter = 0;

    private WeightedQuickUnionUF uf;

    /**
     * Create n-by-n grid, with all sites blocked
     * @param n Number of rows and column in the grid. Must be positive.
     */
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be positive.");
        this.n = n;
        topSiteIndex = site(n, n) + 1;
        bottomSiteIndex = topSiteIndex + 1;
        int ufArraySize = n*n + 2; // n^2 for the grid nodes, 1 top site and 1 bottom site
        uf = new WeightedQuickUnionUF(ufArraySize);
        opened = new boolean[ufArraySize];
        initTopAndBottomSites();
    }

    /**
     * Connects all the top row sites to the virtual top site,
     * and all the bottom row sites - to the virtual bottom site.
     */
    private void initTopAndBottomSites() {
        for (int i = 1; i <= n; i++) {
            uf.union(topSiteIndex, site(1, i));
            uf.union(bottomSiteIndex, site(n, i));
        }
    }

    /**
     * Returns the site's index in the UF array.
     * @param i row number, an integer between 1 and n.
     * @param j column number, an integer between 1 and n.
     * @return linear index for UF array access, an integer between 0 and (n-1)^2.
     */
    private int site(int i, int j) {
        return n*(i - 1) + (j - 1);
    }

    /**
     * Open site (row, col) if it is not open already
     * @param row index between 1 and n.
     * @param col index between 1 and n.
     */
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            int site = site(row, col);
            open(site);
            connectToTheOpenAdjacentSites(row, col);
        }
    }

    private void open(int site) {
        if (!opened[site]) {
            opened[site] = true;
            openSitesCounter++;
        }
    }

    private void connectToTheOpenAdjacentSites(int row, int col) {
        int site = site(row, col);
        if (row < n) {
            int bottomNeighbour = site(row + 1, col);
            if (isOpen(bottomNeighbour)) uf.union(site, bottomNeighbour);
        }
        if (row > 1) {
            int topNeighbour = site(row - 1, col);
            if (isOpen(topNeighbour)) uf.union(site, topNeighbour);
        }
        if (col < n) {
            int rightNeighbour = site(row, col + 1);
            if (isOpen(rightNeighbour)) uf.union(site, rightNeighbour);
        }
        if (col > 1) {
            int leftNeighbour = site(row, col -1);
            if (isOpen(leftNeighbour)) uf.union(site, leftNeighbour);
        }
    }

    /**
     * Is site (row, col) open?
     * @param row index between 1 and n.
     * @param col index between 1 and n.
     * @return
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return opened[site(row, col)];
    }

    private boolean isOpen(int site) {
        return opened[site];
    }

    /**
     * Is site (row, col) full?
     * @param row index between 1 and n.
     * @param col index between 1 and n.
     * @return <tt>true</tt>, if the site is open and connected via chain
     * to at least one site at the top row.
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        int site = site(row, col);
        return isOpen(site) && uf.connected(site, topSiteIndex);
    }

    /**
     * Number of open sites
     * @return Current open sites number.
     */
    public int numberOfOpenSites() {
        return openSitesCounter;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n) {
            throwIae("row");
        } else if (col < 1 || col > n) {
            throwIae("column");
        }
    }

    private void throwIae(String cause) {
        throw new IllegalArgumentException(String.format(
                "Incorrect %s number. The value must be between 1 and %d", cause, n));
    }

    /**
     * Does the system percolate?
     * @return <tt>true</tt>, if the system percolates.
     */
    public boolean percolates() {
        return uf.connected(topSiteIndex, bottomSiteIndex);
    }

    public static void main(String[] args) {
        // test client (optional)
    }

}
