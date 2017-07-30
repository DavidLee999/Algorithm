import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class Quick3way {
    private Quick3way() {};

    private static boolean less( Comparable v, Comparable w )
    { return v.compareTo( w ) < 0; }

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

    private static boolean isSorted( Comparable[] a )
    { return isSorted( a, 0, a.length - 1); }

    public static void sort( Comparable[] a, int lo, int hi )
    {
        if( hi <= lo )
            return;

        int lt = lo, gt = hi;
        Comparable t = a[lo];
        int i = lo;

        while( i <= gt ) {
            int cmp = a[i].compareTo( t );

            if( cmp < 0 )
                exch( a, lt++, i++ );
            else if( cmp > 0 )
                exch( a, i, gt-- );
            else
                ++i;
        }

        sort( a, lo, lt - 1 );
        sort( a, gt + 1, hi );
        assert isSorted( a, lo, hi );
    }
    public static void sort( Comparable[] a )
    {
        StdRandom.shuffle( a );
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
        Quick3way.sort( a );
        show( a );
    }
}
