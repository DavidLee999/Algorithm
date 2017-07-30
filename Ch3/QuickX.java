import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class QuickX {
    private QuickX() {}

    private static final int INSERTION_SORT_CUTOFF = 8;
    private static final int MEDIAN_OF_3_CUTOFF = 40;

    private static boolean less( Comparable v, Comparable w )
    { return v.compareTo( w ) < 0; }

    private static boolean eq( Comparable v, Comparable w )
    { return v.compareTo( w ) == 0; }

    private static void exch( Object[] a, int i, int j )
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted( Comparable[] a, int lo, int hi )
    {
        for( int i = lo + 1; i <= hi; ++i )
            if( less( a[i], a[i - 1] ) )
                return false;

        return true;
    }

    private static int median3( Comparable[] a, int i, int j, int k )
    {
        return ( less( a[i], a[j] ) ) ?
            ( less( a[j], a[k] )  ? j :  less( a[i], a[k] ) ? k : i ) :
            ( less( a[k], a[j] )  ? j :  less( a[i], a[k] ) ? i : k );
    }

    private static void insertionSort( Comparable[] a, int lo, int hi )
    {
        for( int i = lo + 1; i <= hi; ++i )
            for( int j = i; j > lo && less( a[j], a[j - 1] ); --j )
                exch( a, j, j - 1 );
    }

    private static boolean isSorted( Comparable[] a )
    { return isSorted( a, 0, a.length - 1 ); }

    private static void sort( Comparable[] a, int lo, int hi )
    {
        int n = hi - lo + 1;

        if( n <= INSERTION_SORT_CUTOFF )
        {
            insertionSort( a, lo, hi );
            return;
        }

        else if( n <= MEDIAN_OF_3_CUTOFF )
        {
            int m = median3( a, lo, lo + n/2, hi );
            exch( a, lo, m );
        }

        else
        {
            int eps = n / 8;
            int mid = lo + n / 2;

            int m1 = median3( a, lo, lo + eps, lo + eps + eps );
            int m2 = median3( a, mid - eps, mid, mid + eps );
            int m3 = median3( a, hi - eps - eps, hi - eps, hi );

            int ninther = median3( a, m1, m2, m3 );
            exch( a, lo, ninther );
        }

        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        Comparable v = a[lo];
        while( true ) {
            while( less( a[++i], v) )
                if( i == hi )
                    break;

            while( less( v, a[--j] ) ) {}

            if( i == j && eq( a[i], v ) )// move elements equal to v to the left
                exch( a, ++p, i );
            if( i >= j )
                break;

            exch( a, i, j );

            if( eq( a[i], v ) ) // move elements equal to v to the left and right
                exch( a, ++p, i );
            if( eq( a[j], v ) )
                exch( a, --q, j );
        }

        i = j + 1;
        for( int k = lo; k <= p; ++k ) // move elements equal to v to the right pos
            exch( a, k, j-- );
        for( int k = hi; k >= q; k-- ) // maintain the pointer lt and gt
            exch( a, k, i++ );

        sort( a, lo, j );
        sort( a, i, hi );
    }

    public static void sort( Comparable[] a )
    {
        sort( a, 0, a.length - 1 );
        assert isSorted( a );
    }

    public static void show( Comparable[] a )
    {
        for( int i = 0; i < a.length; ++i )
            System.out.print( a[i] + " " );

        System.out.println();
    }

    public static void main( String[] args )
    {
        String[] a = StdIn.readAllStrings();
        QuickX.sort( a );
        show( a );
    }
}

