import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {

    private TreeSet<Point2D> points;

    public PointSET()
    {
        points = new TreeSet<Point2D>();
    }

    public boolean isEmpty()
    {
        return points.isEmpty();
    }

    public int size()
    {
        return points.size();
    }

    public void insert(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("The point provided to insert() is null!");

        if (!points.contains(p))
            points.add(p);
    }

    public boolean contains(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("The point provide to contains() is null!");

        return points.contains(p);
    }

    public void draw()
    {
        for (Point2D p : points)
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect)
    {
        if (rect == null)
            throw new IllegalArgumentException("The rect provided to range() is null!");

        List<Point2D> res = new LinkedList<Point2D>();

        Point2D upperleft = new Point2D(rect.xmin(), rect.ymax());
        Point2D lowerright = new Point2D(rect.xmax(), rect.ymin());

        for (Point2D p : points)
        {
            if (upperleft.x() <= p.x() && p.x() <= lowerright.x())
                if (lowerright.y() <= p.y() && p.y() <= upperleft.y())
                    res.add(p);
        }

        return res;
    }

    public Point2D nearest(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("The point provided to nearest() is null!");

        double minDist = Double.MAX_VALUE;
        double dist;
        Point2D res = null;
        for (Point2D candidates : points)
        {
            dist = p.distanceTo(candidates);
            if (dist < minDist)
            {
                minDist = dist;
                res = candidates;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
