package ru.iav.std.algorithms.p1.w5.task;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.NoSuchElementException;

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

        Point2D floor, ceiling;
        try {
            floor = set.floor(p);
        } catch (NoSuchElementException ex) {
            return set.min();   // all keys are greater than p
        }
        try {
            ceiling = set.ceiling(p);
        } catch (NoSuchElementException ex) {
            return set.max();   // all keys are less than p
        }
        if (Double.compare(floor.distanceSquaredTo(p), ceiling.distanceSquaredTo(p)) < 0)   return floor;
        else                                                                                return ceiling;
    }

    /**
     * unit testing of the methods (optional)
     * @param args
     */
    public static void main(String[] args){}

}
