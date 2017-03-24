package ru.iav.std.algorithms.p1.w5.task;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

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
        private final Point2D value;
        private Node left, right;
        private int size;

        public Node(double key, Point2D value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }

        public boolean find(Point2D value) {
            int cmp = compareTo(value);
            if (cmp > 0)        return left != null && left.find(value);
            else if (cmp < 0)   return right != null && right.find(value);
            else                return this.value.equals(value);
        }

        public Set<Point2D> range(RectHV rect) {
            Set<Point2D> set = new TreeSet<>();
            range(rect, set);
            return set;
        }

        private void range(RectHV rect, Set<Point2D> set) {
            if (rect.contains(this.value))  set.add(this.value);

            int cmp = compareTo(rect);
            if (cmp > -1 && left != null)   left.range(rect, set);
            if (cmp < 1 && right != null)   right.range(rect, set);
        }

        public Point2D nearest(Point2D queryPoint) {
            return nearest(queryPoint, null, unitRect());
        }

        private RectHV unitRect() {
            return new RectHV(0.0, 0.0, 1.0, 1.0);
        }

        private Point2D nearest(Point2D queryPoint, Point2D currentlyNearest, RectHV rect) {
            if (currentlyNearest == null
                    || queryPoint.distanceSquaredTo(this.value) < queryPoint.distanceSquaredTo(currentlyNearest))
                currentlyNearest = this.value;

            if (left == null)
                return right == null ?
                        currentlyNearest : right.nearest(queryPoint, currentlyNearest, cutRect(rect, right));
            if (right == null)
                return left.nearest(queryPoint, currentlyNearest, cutRect(rect, left));

            RectHV leftRect = cutRect(rect, left);
            RectHV rightRect = cutRect(rect, right);
            if (leftRect.distanceTo(queryPoint) < rightRect.distanceTo(queryPoint)) {
                Point2D leftNearest = left.nearest(queryPoint, currentlyNearest, leftRect);
                return leftNearest.equals(currentlyNearest) ?
                        right.nearest(queryPoint, currentlyNearest, rightRect) : leftNearest;
            } else {
                Point2D rightNearest = right.nearest(queryPoint, currentlyNearest, rightRect);
                return rightNearest.equals(currentlyNearest) ?
                        left.nearest(queryPoint, currentlyNearest, leftRect) : rightNearest;
            }
        }

        abstract RectHV cutRect(RectHV rect, Node child);

        public void put(Point2D point) {
            int cmp = compareTo(point);
            if (cmp > 0)        putToTheLeft(point);
            else if (cmp < 0)   putToTheRight(point);
            // TODO equal coords processing ??
            size = 1 + size(left) + size(right);
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
        public XNode(Point2D value) {
            super(value.x(), value, 1);
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
        RectHV cutRect(RectHV rect, Node child) {
            assert rect != null;
            assert child != null;
            double minX = rect.xmin(), maxX = rect.xmax();
            double middleX = child.value.x();
            if (compareThisKeyTo(middleX) < 0)  minX = middleX;
            else                                maxX = middleX;
            return new RectHV(minX, rect.ymin(), maxX, rect.ymax());
        }

    }

    private class YNode extends Node {
        public YNode(Point2D value) {
            super(value.y(), value, 1);
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
        RectHV cutRect(RectHV rect, Node child) {
            assert rect != null;
            assert child != null;
            double minY = rect.ymin(), maxY = rect.ymax();
            double middleY = child.value.y();
            if (compareThisKeyTo(middleY) < 0)  minY = middleY;
            else                                maxY = middleY;
            return new RectHV(rect.xmin(), minY, rect.xmax(), maxY);
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
