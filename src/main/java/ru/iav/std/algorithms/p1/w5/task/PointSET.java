package ru.iav.std.algorithms.p1.w5.task;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

/**
 * Created by takoe on 24.03.17.
 */
public class PointSET {

    private SET<Point2D> set;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        set = new SET<>();
    }

    /**
     * is the set empty?
     * @return
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * number of points in the set
     * @return
     */
    public int size() {
        return set.size();
    }

    /**
     * add the point to the set (if it is not already in the set)
     * @param p
     */
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException("Argument should not be null.");
        set.add(p);
    }

    /**
     * does the set contain point p?
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException("Argument should not be null.");
        return set.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        set.forEach(Point2D::draw);
    }

    /**
     * all points that are inside the rectangle
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException("Argument should not be null.");
        SET<Point2D> result = new SET<>();
        for (Point2D point : set) {
            if (rect.contains(point))
                result.add(point);
        }
        return result;
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException("Argument should not be null.");
        if (set.isEmpty()) return null;

        double minD = Double.POSITIVE_INFINITY;
        Point2D nearest = set.min();
        for (Point2D point : set) {
            double d = point.distanceSquaredTo(p);
            if (d < minD) {
                minD = d;
                nearest = point;
            }
        }
        return nearest;
    }

    /**
     * unit testing of the methods (optional)
     * @param args
     */
    public static void main(String[] args){}

}
