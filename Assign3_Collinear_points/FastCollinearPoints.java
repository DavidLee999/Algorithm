import java.util.Arrays;
import java.util.ArrayList;
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
        ArrayList<LineSegment> l = new ArrayList<>();

        for( int i = 0; i < n; ++i )
        {
            Point p = pointsByCoords[i];
            Point[] pointsBySlope = pointsByCoords.clone(); // first sorted by coordinates

            Arrays.sort( pointsBySlope, p.slopeOrder() );

            double[] slopeP = new double[n];
            for( int j = 0; j < n; ++j )
                slopeP[j] = p.slopeTo( pointsBySlope[j] );

            int j = 1;
            while( j < n ) {
                ArrayList<Point> candidates = new ArrayList<Point>();
                final double REF = slopeP[j];
                do{
                    candidates.add( pointsBySlope[j++] );
                }while( j < n && slopeP[j] == REF );

                if( candidates.size() >= 3 && p.compareTo( candidates.get(0) ) < 0)
                    l.add( new LineSegment( p, candidates.get(candidates.size() - 1 ) ) );
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
