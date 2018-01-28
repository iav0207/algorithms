package ru.iav.std.algorithms.p2.w2.task;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private static final double BORDER_PIXEL_ENERGY = 1_000d;

    private Picture current;

    private int[][] px;
    private int height, width;

    /**
     * create a seam carver object based on the given picture
     */
    public SeamCarver(Picture picture) {
        checkArgument(picture != null);

        // noinspection ConstantConditions
        this.current = new Picture(picture);

        this.height = picture.height();
        this.width = picture.width();
        this.px = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                px[x][y] = picture.getRGB(x, y);
            }
        }
    }

    /**
     * Current picture
     */
    public Picture picture() {
        final int h = height(), w = width();
        if (current.height() == h && current.width() == w) {
            return current;
        }
        current = new Picture(w, h);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                current.setRGB(x, y, v(x, y));
            }
        }
        return current;
    }

    /**
     * Width of current picture
     */
    public int width() {
        return width;
    }

    /**
     * Height of current picture
     */
    public int height() {
        return height;
    }

    /**
     * Energy of pixel at column x and row y, dual-gradient energy function
     *
     * We define the energy of a pixel at the border of the image to be 1000,
     * so that it is strictly larger than the energy of any interior pixel.
     */
    public double energy(int x, int y) {
        checkArgument(validCoord(x, width()), validCoord(y, height()));
        return isBorderPixel(x, y) ? BORDER_PIXEL_ENERGY : Math.sqrt(xGradientSquared(x, y) + yGradientSquared(x, y));
    }

    private boolean isBorderPixel(int x, int y) {
        return x == 0 || y == 0 || x == width() - 1 || y == height() - 1;
    }

    private double xGradientSquared(int x, int y) {
        return gradientSquared(color(x - 1, y), color(x + 1, y));
    }

    private double yGradientSquared(int x, int y) {
        return gradientSquared(color(x, y - 1), color(x, y + 1));
    }
    
    private static double gradientSquared(Color from, Color to) {
        double rDiff = to.getRed() - from.getRed();
        double gDiff = to.getGreen() - from.getGreen();
        double bDiff = to.getBlue() - from.getBlue();
        return squared(rDiff) + squared(gDiff) + squared(bDiff);
    }

    private Color color(int x, int y) {
        return new Color(v(x, y));
    }

    private int v(int x, int y) {
        return px[x][y];
    }

    private static double squared(double val) {
        return Math.pow(val, 2);
    }

    /**
     * Sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() {
        final int h = height(), w = width(), noVal = -1;
        double[][] nrg = energies();
        double[] nrgSum = new double[h];
        double[] aux = new double[h];
        int[][] to = new int[w][h];
        for (int y = 0; y < h; y++) {
            to[0][y] = noVal;
            nrgSum[y] = energy(0, y);
            if (w > 1) {
                to[1][y] = y;
                nrgSum[y] += energy(1, y);
            }
        }

        for (int x = 2; x < w; x++) {
            System.arraycopy(nrgSum, 0, aux, 0, aux.length);
            for (int y = 0; y < h; y++) {
                double minNrg = Double.POSITIVE_INFINITY;
                int edge = noVal;
                for (int adjY : adjHorizontal(x - 1, y)) {
                    double xyNrg = nrg[x - 1][adjY];
                    if (xyNrg < minNrg) {
                        minNrg = xyNrg;
                        edge = adjY;
                    }
                }
                edge = edge == noVal ? y : edge;
                to[x][y] = edge;
                nrgSum[y] = aux[edge] + minNrg;
            }
        }

        int[] seam = new int[w];
        for (int x = w - 1, y = minValueIndex(nrgSum); x >= 0; x--) {
            seam[x] = y;
            y = to[x][y] == noVal ? y : to[x][y];
        }
        return seam;
    }

    private Iterable<Integer> adjHorizontal(int x, int y) {
        List<Integer> adj = new ArrayList<>(3);
        if (x >= width() - 1) {
            return Collections.emptyList();
        }
        adj.add(y);
        if (y > 0) {
            adj.add(y - 1);
        }
        if (y < height() - 1) {
            adj.add(y + 1);
        }
        return adj;
    }

    /**
     * Sequence of indices for vertical seam
     */
    public int[] findVerticalSeam() {
        final int h = height(), w = width(), noVal = -1;
        double[][] nrg = energies();
        double[] nrgSum = new double[w];
        double[] aux = new double[w];
        int[][] to = new int[w][h];
        for (int x = 0; x < w; x++) {
            to[x][0] = noVal;
            nrgSum[x] = energy(x, 0);
            if (h > 1) {
                to[x][1] = x;
                nrgSum[x] += energy(x, 1);
            }
        }

        for (int y = 2; y < h; y++) {
            System.arraycopy(nrgSum, 0, aux, 0, aux.length);
            for (int x = 0; x < w; x++) {
                double minNrg = Double.POSITIVE_INFINITY;
                int edge = noVal;
                for (int adjX : adjVertical(x, y - 1)) {
                    double xyNrg = nrg[adjX][y - 1];
                    if (xyNrg < minNrg) {
                        minNrg = xyNrg;
                        edge = adjX;
                    }
                }
                edge = edge == noVal ? x : edge;
                to[x][y] = edge;
                nrgSum[x] = aux[edge] + minNrg;
            }
        }

        int[] seam = new int[h];
        for (int y = h - 1, x = minValueIndex(nrgSum); y >= 0; y--) {
            seam[y] = x;
            x = to[x][y] == noVal ? x : to[x][y];
        }
        return seam;
    }

    private Iterable<Integer> adjVertical(int x, int y) {
        List<Integer> adj = new ArrayList<>(3);
        if (y >= height() - 1) {
            return Collections.emptyList();
        }
        adj.add(x);
        if (x > 0) {
            adj.add(x - 1);
        }
        if (x < width() - 1) {
            adj.add(x + 1);
        }
        return adj;
    }

    private int minValueIndex(double[] array) {
        int idx = 0;
        double min = array[idx];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                idx = i;
            }
        }
        return idx;
    }

    private double[][] energies() {
        final int h = height(), w = width();
        double[][] nrg = new double[w][h];
        for (int x = 0; x < w; x++)
            for (int y = 0; y < h; y++)
                nrg[x][y] = energy(x, y);
        return nrg;
    }

    /**
     * Remove horizontal seam from current picture
     */
    public void removeHorizontalSeam(int[] seam) {
        checkArgument(seam != null);
        // noinspection ConstantConditions
        checkArgument(seam.length == width());
        checkArgument(height > 1);
        int prev = seam[0];
        for (int x = 0; x < seam.length; x++) {
            int seamY = seam[x];
            checkArgument(Math.abs(seamY - prev) <= 1);
            System.arraycopy(px[x], seamY + 1, px[x], seamY, height - 1 - seamY);
            prev = seamY;
        }
        height--;
    }

    /**
     * Remove vertical seam from current picture
     */
    public void removeVerticalSeam(int[] seam) {
        checkArgument(seam != null);
        // noinspection ConstantConditions
        checkArgument(seam.length == height());
        checkArgument(width > 1);
        int prev = seam[0];
        for (int y = 0; y < seam.length; y++) {
            int seamX = seam[y];
            checkArgument(Math.abs(seamX - prev) <= 1);
            for (int x = seamX; x < width - 1; x++) {
                px[x][y] = px[x + 1][y];
            }
            prev = seamX;
        }
        width--;
    }

    private boolean validCoord(int coord, int bound) {
        return coord >= 0 && coord < bound;
    }

    private static void checkArgument(boolean... conditions) {
        for (boolean condition : conditions) {
            if (!condition) throw new IllegalArgumentException();
        }
    }
}
