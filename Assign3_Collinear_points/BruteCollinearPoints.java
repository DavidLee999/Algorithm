import java.util.Arrays;
import java.util.ArrayList;

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
    {}
}
