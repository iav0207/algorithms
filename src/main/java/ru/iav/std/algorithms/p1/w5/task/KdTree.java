package ru.iav.std.algorithms.p1.w5.task;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Objects.requireNonNull;

/**
 * Created by takoe on 24.03.17.
 */
public class KdTree {

    private Node root;

    private abstract class Node {

        private final double key;
        final Point2D value;
        private Node left, right;
        private int size = 1;

        Node(double key, Point2D value) {
            this.key = key;
            this.value = value;
        }

        public boolean find(Point2D value) {
            int cmp = whereToGoNext(value);
            if (cmp > 0)        return left != null && left.find(value);
            else if (cmp < 0)   return right != null && right.find(value);
            else                return true;
        }

        public Set<Point2D> range(RectHV rect) {
            Set<Point2D> set = new TreeSet<>();
            range(rect, set);
            return set;
        }

        private void range(RectHV rect, Set<Point2D> set) {
            if (rect.contains(this.value))
                set.add(this.value);

            int cmp = compareTo(rect);
            if (cmp > -1 && left != null)   left.range(rect, set);
            if (cmp < 1 && right != null)   right.range(rect, set);
        }

        Set<Point2D> getAllPoints() {
            return addPoints(new HashSet<>());
        }

        private Set<Point2D> addPoints(Set<Point2D> set) {
            set.add(value);
            if (left != null)   left.addPoints(set);
            if (right != null)  right.addPoints(set);
            return set;
        }

        List<Line> getSubdivisionLines() {
            return getSubdivisionLines(unitRect(), new ArrayList<>(size()));
        }

        List<Line> getSubdivisionLines(RectHV rect, List<Line> lines) {
            lines.add(getSubdivisionLine(rect));
            if (left != null)   left.getSubdivisionLines(cutRect(rect, left), lines);
            if (right != null)  right.getSubdivisionLines(cutRect(rect, right), lines);
            return lines;
        }

        abstract Line getSubdivisionLine(RectHV rect);

        Point2D nearest(Point2D queryPoint) {
            return nearest(queryPoint, this.value, unitRect());
        }

        private RectHV unitRect() {
            return new RectHV(0.0, 0.0, 1.0, 1.0);
        }

        private Point2D nearest(Point2D queryPoint, Point2D currentlyNearest, RectHV rect) {
            if (queryPoint.distanceSquaredTo(this.value) < queryPoint.distanceSquaredTo(currentlyNearest))
                currentlyNearest = this.value;

            if (left == null)
                return right == null ?
                        currentlyNearest : right.nearest(queryPoint, currentlyNearest, cutRect(rect, right));
            if (right == null)
                return left.nearest(queryPoint, currentlyNearest, cutRect(rect, left));

            RectHV leftRect = cutRect(rect, left);
            RectHV rightRect = cutRect(rect, right);

            // pruning
            double currentDistance = currentlyNearest.distanceTo(queryPoint);
            if (currentDistance < leftRect.distanceTo(queryPoint))
                return right.nearest(queryPoint, currentlyNearest, rightRect);
            if (currentDistance < rightRect.distanceTo(queryPoint))
                return left.nearest(queryPoint, currentlyNearest, leftRect);

            // going down the branch lying at the query point side first
            int cmp = whereToGoNext(queryPoint);
            if (cmp > 0) {
                // going to the left
                currentlyNearest = left.nearest(queryPoint, currentlyNearest, leftRect);
                // pruning again
                if (currentlyNearest.distanceTo(queryPoint) < rightRect.distanceTo(queryPoint))
                    return currentlyNearest;
                else    // going to the right
                    return right.nearest(queryPoint, currentlyNearest, rightRect);
            } else if (cmp < 0) {
                // going to the right
                currentlyNearest = right.nearest(queryPoint, currentlyNearest, rightRect);
                // pruning again
                if (currentlyNearest.distanceTo(queryPoint) < leftRect.distanceTo(queryPoint))
                    return currentlyNearest;
                else    // going to the left
                    return left.nearest(queryPoint, currentlyNearest, leftRect);
            }
            return currentlyNearest;
        }

        abstract RectHV cutRect(RectHV rect, Node child);

        void put(Point2D point) {
            int cmp = whereToGoNext(point);
            if (cmp > 0)        putToTheLeft(point);
            else if (cmp < 0)   putToTheRight(point);
            size = 1 + size(left) + size(right);
        }

        private int whereToGoNext(Point2D point) {
            int cmpKey = compareTo(point);
            if (cmpKey == 0)    return this.value.compareTo(point); // full comparison:
                                                                    // equal coordinates (one of two) processing
            else                return cmpKey;
        }

        public abstract int compareTo(Point2D value);

        private void putToTheLeft(Point2D point) {
            if (left == null)   left = createChild(point);
            else                left.put(point);
        }

        private void putToTheRight(Point2D point) {
            if (right == null)  right = createChild(point);
            else                right.put(point);
        }

        public abstract Node createChild(Point2D value);

        public abstract int compareTo(RectHV rectangle);

        int compareThisKeyTo(double key) {
            return Double.compare(this.key, key);
        }
    }

    private class XNode extends Node {
        XNode(Point2D value) {
            super(value.x(), value);
        }

        @Override
        public int compareTo(Point2D value) {
            return compareThisKeyTo(value.x());
        }

        @Override
        public Node createChild(Point2D value) {
            return new YNode(value);
        }

        @Override
        public int compareTo(RectHV rectangle) {
            if (compareThisKeyTo(rectangle.xmin()) < 0)         return -1;
            else if (compareThisKeyTo(rectangle.xmax()) > 0)    return 1;
            else                                                return 0;
        }

        @Override
        Line getSubdivisionLine(RectHV rect) {
            return Line.vertical(value.x(), rect.ymin(), rect.ymax());
        }

        @Override
        RectHV cutRect(RectHV rect, Node child) {
            assert rect != null;
            assert child != null;
            
            double minX = rect.xmin();
            double maxX = rect.xmax();
            double thisX = this.value.x();
            double childX = child.value.x();
            
            assert minX <= thisX && thisX <= maxX;
            assert minX <= childX && childX <= maxX;

            int cmp = compareThisKeyTo(childX);
            if (cmp < 0)        minX = thisX;
            else if (cmp > 0)   maxX = thisX;
            
            return new RectHV(minX, rect.ymin(), maxX, rect.ymax());
        }

    }

    private class YNode extends Node {
        YNode(Point2D value) {
            super(value.y(), value);
        }

        @Override
        public int compareTo(Point2D value) {
            return compareThisKeyTo(value.y());
        }

        @Override
        public Node createChild(Point2D value) {
            return new XNode(value);
        }

        @Override
        public int compareTo(RectHV rectangle) {
            if (compareThisKeyTo(rectangle.ymin()) < 0)         return -1;
            else if (compareThisKeyTo(rectangle.ymax()) > 0)    return 1;
            else                                                return 0;
        }

        @Override
        Line getSubdivisionLine(RectHV rect) {
            return Line.horizontal(value.y(), rect.xmin(), rect.xmax());
        }

        @Override
        RectHV cutRect(RectHV rect, Node child) {
            assert rect != null;
            assert child != null;
            
            double minY = rect.ymin();
            double maxY = rect.ymax();
            double thisY = this.value.y();
            double childY = child.value.y();
            
            assert minY <= thisY && thisY <= maxY;
            assert minY <= childY && childY <= maxY;

            int cmp = compareThisKeyTo(childY);
            if (cmp < 0)        minY = thisY;
            else if (cmp > 0)   maxY = thisY;

            return new RectHV(rect.xmin(), minY, rect.xmax(), maxY);
        }

    }

    private static class Line {
        double x1, x2, y1, y2;
        Color color = Color.BLACK;
        Line(double x1, double x2, double y1, double y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
        static Line vertical(double x, double y1, double y2) {
            return new Line(x, x, y1, y2).withColor(StdDraw.RED);
        }
        static Line horizontal(double y, double x1, double x2) {
            return new Line(x1, x2, y, y).withColor(StdDraw.BLUE);
        }
        Line withColor(Color color) {
            this.color = color;
            return this;
        }
        void draw() {
            Color prev = StdDraw.getPenColor();
            StdDraw.setPenColor(color);
            StdDraw.line(x1, y1, x2, y2);
            StdDraw.setPenColor(prev);
        }
    }

    /**
     * construct an empty set of points
     */
    public KdTree() {
    }

    /**
     * is the set empty?
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * number of points in the set
     * @return
     */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    /**
     * add the point to the set (if it is not already in the set)
     *
     * The algorithms for search and insert are similar to those for BSTs,
     * but at the root we use the x-coordinate (if the point to be inserted
     * has a smaller x-coordinate than the point at the root, go left; otherwise go right);
     * then at the next level, we use the y-coordinate (if the point to be inserted
     * has a smaller y-coordinate than the point in the node, go left; otherwise go right);
     * then at the next level the x-coordinate, and so forth.
     */
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException("Argument should not be null.");
        if (root == null)
            root = new XNode(p);
        else
            root.put(p);
    }

    /**
     * does the set contain point p?
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        requireNonNull(p, "Argument should not be null.");
        return root != null && root.find(p);
    }

    /**
     * draw all points to standard draw
     *
     * A 2d-tree divides the unit square in a simple way:
     * all the points to the left of the root go in the left subtree;
     * all those to the right go in the right subtree; and so forth, recursively.
     * Your draw() method should draw all of the points to standard draw in black
     * and the subdivisions in red (for vertical splits) and blue (for horizontal splits).
     * This method need not be efficient—it is primarily for debugging.
     */
    public void draw() {
        root.getAllPoints().forEach(Point2D::draw);
        root.getSubdivisionLines().forEach(Line::draw);
    }

    /**
     * all points that are inside the rectangle
     *
     * To find all points contained in a given query rectangle, start at the root
     * and recursively search for points in both subtrees using the following pruning rule:
     * if the query rectangle does not intersect the rectangle corresponding to a node,
     * there is no need to explore that node (or its subtrees).
     * A subtree is searched only if it might contain a point contained in the query rectangle.
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException("Argument should not be null.");
        return root == null ? new TreeSet<>() : root.range(rect);
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * To find a closest point to a given query point, start at the root
     * and recursively search in both subtrees using the following pruning rule:
     * if the closest point discovered so far is closer than the distance
     * between the query point and the rectangle corresponding to a node,
     * there is no need to explore that node (or its subtrees).
     * That is, a node is searched only if it might contain a point that is closer
     * than the best one found so far. The effectiveness of the pruning rule
     * depends on quickly finding a nearby point. To do this, organize your recursive method
     * so that when there are two possible subtrees to go down, you always choose
     * the subtree that is on the same side of the splitting line as the query point
     * as the first subtree to explore—the closest point found while exploring
     * the first subtree may enable pruning of the second subtree.
     */
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException("Argument should not be null.");
        return isEmpty() ? null : root.nearest(p);
    }

    /**
     * unit testing of the methods (optional)
     * @param args
     */
    public static void main(String[] args){}
}
