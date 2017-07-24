import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
    private LineSegment[] lines;

    public FastCollinearPoints( Point[] points )
    {

        if( points == null )
            throw new IllegalArgumentException( "The array Points is null!" );

        for( Point p : points )
        {
            if( p == null )
                throw new IllegalArgumentException( "The array Points contains null elements!" );
        }

        Point[] pointsByCoords = points.clone();
        Arrays.sort( pointsByCoords );

        for( int i = 1; i < pointsByCoords.length; ++i )
        {
            if( pointsByCoords[i].compareTo( pointsByCoords[i-1] ) == 0 )
                throw new IllegalArgumentException( "the array Points contains repeated points!" );
        }

        int n = points.length;
        List<LineSegment> l = new LinkedList<>();

        for( int i = 0; i < n; ++i )
        {
            Point p = points[i];
            Point[] pointsBySlope = pointsByCoords.clone(); // first sorted by coordinates

            Arrays.sort( pointsBySlope, p.slopeOrder() );

            int j = 1;
            while( j < n ){
                LinkedList<Point> candidates = new LinkedList<Point>();
                final double REF = p.slopeTo( pointsBySlope[j] );

                do {
                    candidates.add( pointsBySlope[j++] );
                } while( j < n && p.slopeTo( pointsBySlope[j] ) == REF );

                if( candidates.size() >= 3 && p.compareTo( candidates.peek() ) < 0 )
                    l.add( new LineSegment( p, candidates.peekLast() ) );
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

        FastCollinearPoints collinear = new FastCollinearPoints( p );
        for( LineSegment l : collinear.segments() )
        {
            StdOut.println( l );
            l.draw();
        }

        StdDraw.show();
    }
}
