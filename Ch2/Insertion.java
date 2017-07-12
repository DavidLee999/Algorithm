import java.util.Comparator;

public class Insertion {
    private Insertion() {}

    private static boolean less( Comparable v, Comparable w )
    {
        return v.compareTo( w ) < 0;
    }

    private static boolean less( Object v, Object w, Comparator comparator )
    {
        return comparator.compare( v, w ) < 0;
    }

    private static void exch( Object[] a, int i, int j )
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted( Comparable[] a, int lo, int hi )
    {
        for( int i = lo + 1; i < hi; ++i )
            if( less( a[i], a[i-1] ) )
                return false;

        return true;
    }

    private static boolean isSorted( Comparable[] a )
    {
        return isSorted( a, 0, a.length );
    }

    private static boolean isSorted( Object[]a, int lo, int hi, Comparator comparator )
    {
        for( int i = lo + 1; i < hi; ++i )
            if( less( a[i], a[i-1], comparator ) )
                return false;

        return true;
    }
    private static boolean isSorted( Object[] a, Comparator comparator )
    {
        return isSorted(a, 0, a.length, comparator );
    }

    public static void sort( Comparable[] a )
    {
        int N = a.length;

        for( int i = 1; i < N; ++i )
        {
            for( int j = i; j > 0 && less( a[j], a[j-1] ); --j )
                exch( a, j, j-1 );

            assert isSorted( a, 0, i );
        }
        assert isSorted( a );
    }

    public static void sort( Object[] a, Comparator comparator )
    {
        int N = a.length;

        for( int i = 1; i < N; ++i )
        {
            for( int j = i; j > 0 && less( a[j], a[j-1], comparator ); ++j )
                exch( a, j, j-1 );

            assert isSorted( a, 0, i, comparator );
        }

        assert isSorted( a, comparator );
    }

    public static void show( Comparable[] a )
    {
        for( int i = 0; i < a.length; ++i )
            System.out.print( a[i] + " " );
    }

    public static void main(String[] args)
    {
        Integer[] a = new Integer[] {9,8,7,6,5,4,3,3,1};
        Insertion.sort( a );
        show( a );
    }
}
