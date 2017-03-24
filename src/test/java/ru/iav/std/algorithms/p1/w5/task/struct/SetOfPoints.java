package ru.iav.std.algorithms.p1.w5.task.struct;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

/**
 * Created by takoe on 24.03.17.
 */
public interface SetOfPoints {
    boolean isEmpty();
    int size();
    void insert(Point2D p);
    boolean contains(Point2D p);
    void draw();
    Iterable<Point2D> range(RectHV rect);
    Point2D nearest(Point2D p);
}
