import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {

    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points)
    {
        if( points == null )
            throw new IllegalArgumentException( "The array Points is null!" );

        for( Point p : points )
        {
            if( p == null )
                throw new IllegalArgumentException( "The array Points contains null elements!" );
        }

        Point[] sortedPoints = points.clone();
        Arrays.sort( sortedPoints );

        for( int i = 1; i < points.length; ++i )
        {
            if( points[i].compareTo( points[i-1] ) == 0 )
                throw new IllegalArgumentException( "the array Points contains repeated points!" );
        }

        int n = points.length;
        ArrayList<LineSegment> l = new ArrayList<LineSegment>();

        for( int a = 0; a < n - 3; ++a )
        {
            Point pointA = sortedPoints[a];
            for( int b = a + 1; b < n - 2; ++b )
            {
                Point pointB = sortedPoints[b];
                double slopeAB = pointA.slopeTo( pointB );

                for( int c = b + 1; c < n - 1; ++c )
                {
                    Point pointC = sortedPoints[c];
                    double slopeAC = pointA.slopeTo( pointC );

                    if( slopeAB == slopeAC )
                    {
                        for( int d = c + 1; d < n; ++d )
                        {
                            Point pointD = sortedPoints[d];
                            double slopeAD = pointA.slopeTo( pointD );

                            if( slopeAB ==  slopeAD )
                                l.add( new LineSegment( pointA, pointD ) );
                        }
                    }
                }

            }
        }

        lines = l.toArray( new LineSegment[0] );
    }

    public int numberOfSegments()
    { return lines.length; }

    public LineSegment[] segments()
    { return lines.clone(); }

    public static void main(String[] args)
    {
        In in = new In( args[0] );

        int n = in.readInt();
        Point[] p = new Point[n];
        for( int i = 0; i < n; ++i )
        {
            int x = in.readInt();
            int y = in.readInt();

            p[i] = new Point( x, y );
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale( 0, 32786 );
        StdDraw.setYscale( 0, 32786 );

        for(Point q : p)
            q.draw();

        StdDraw.show();

        BruteCollinearPoints collinear = new BruteCollinearPoints( p );

        for( LineSegment l : collinear.segments() )
        {
            StdOut.println( l );
            l.draw();
        }

        StdDraw.show();
    }
}
