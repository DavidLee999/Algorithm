import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private class Node {
        private Point2D point;
        private boolean direction;
        private Node left; // also below
        private Node right; // also above
        private RectHV enclosedRect;

        Node(Point2D p, boolean direct, RectHV rect)
        {
            point = p;
            direction = direct;
            enclosedRect = rect;
        }

        public boolean direct()
        { return direction; }

        public boolean goleft(Point2D p) // also gobelow?
        {
            if (direction == VERTICAL)
                return p.x() < point.x();
            else
                return p.y() < point.y();
        }

        public RectHV rectLeft()
        {
            if (direction == VERTICAL)
                return new RectHV(enclosedRect.xmin(), enclosedRect.ymin(), point.x(), enclosedRect.ymax());
            else
                return new RectHV(enclosedRect.xmin(), enclosedRect.ymin(), enclosedRect.xmax(), point.y());
        }

        public RectHV rectRight()
        {
            if (direction == VERTICAL)
                return new RectHV(point.x(), enclosedRect.ymin(), enclosedRect.xmax(), enclosedRect.ymax());
            else
                return new RectHV(enclosedRect.xmin(), point.y(), enclosedRect.xmax(), enclosedRect.ymax());
        }
    }

    private Node root;
    private int size;

    public KdTree()
    {
        root = null;
        size = 0;
    }

    public boolean isEmpty()
    { return size == 0; }

    public int size()
    { return size; }

    public void insert(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("The point provide to insert() is null!");

        // if the kdtree is empty
        if (root == null)
        {
            root = new Node(p, VERTICAL, new RectHV(0, 0, 1, 1));
            size++;

            return;
        }

        if (contains(p))
            return;

        Node current = root;
        Node prev = null;
        while (current != null)
        {
            prev = current;
            current = current.goleft(p) ? current.left : current.right; 
        }

        if (prev.goleft(p))
            prev.left = new Node(p, !prev.direct(), prev.rectLeft());
        else
            prev.right = new Node(p, !prev.direct(), prev.rectRight());

        size++;

    }

    public boolean contains(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("The point provided to contains() is null!");

        Node current = root;
        while (current != null) {
            if (current.point.equals(p))
                return true;
            else
                current = current.goleft(p) ? current.left : current.right;
        }

        return false;
    }

    public void draw()
    {
        // to-do
        draw(root);
    }

    private void draw(Node x)
    {
        if (x == null)
            return;

        x.point.draw();
        draw(x.left);
        draw(x.right);
    }

    public Iterable<Point2D> range(RectHV rect)
    {
        if (rect == null)
            throw new IllegalArgumentException("The rect provided to range() is null!");

        List<Point2D> res = new LinkedList<Point2D>();
        insidePoints(root, rect, res);

        return res;

    }

    private void insidePoints(Node t, RectHV rect, List<Point2D> res)
    {
        if (t == null)
            return;

        if (!rect.intersects(t.enclosedRect))
            return;

        Point2D upperright = new Point2D(rect.xmax(), rect.ymin());
        Point2D lowerleft = new Point2D(rect.xmin(), rect.ymin());

        if (rect.contains(t.point))
        {
            res.add(t.point);
            insidePoints(t.left, rect, res);
            insidePoints(t.right, rect, res);
            return;
        }

        if (t.goleft(lowerleft))
            insidePoints(t.left, rect, res);
        if (!t.goleft(upperright))
            insidePoints(t.right, rect, res);

    }

    public Point2D nearest(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("The point provided to nearest() is null!");

        return isEmpty() ? null : nearestPoint(p, root.point, root);
    }

    private Point2D nearestPoint(Point2D p, Point2D closest, Node t)
    {
        if (t == null)
            return closest;

        double minDist = closest.distanceTo(p);
        double dist;
        if (t.enclosedRect.distanceTo(p) < minDist)
        {
            dist = t.point.distanceTo(p);
            if (dist < minDist)
            {
                minDist = dist;
                closest = t.point;
            }

            if (t.goleft(p))
            {
                closest = nearestPoint(p, closest, t.left);
                closest = nearestPoint(p, closest, t.right);
            }
            else
            {
                closest = nearestPoint(p, closest, t.right);
                closest = nearestPoint(p, closest, t.left);
            }
        }

        return closest;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
