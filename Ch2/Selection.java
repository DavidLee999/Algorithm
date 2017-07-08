import java.util.Comparator;
import edu.princeton.cs.algs4.StdIn;

public class Selection {

    private Selection() {}


    public static void sort( Comparable[] a )
    {
        int n = a.length;

        for( int i = 0; i < n; i++ )
        {
            int min = i;

            for( int j = i + 1; j < n; j++ )
            {
                if( less( a[j], a[min] ) )
                    min = j;
            }

            exch( a, i, min );
            assert isSorted( a, 0, i );
        }

        assert isSorted( a );
    }

    public static void sort( Object[] a, Comparator comparator )
    {
        int n = a.length;

        for( int i = 0; i < n; i++ )
        {
            int min = i;

            for( int j = i + 1; j < n; j++ )
            {
                if( less( comparator, a[j], a[min] ) )
                    min = j;
            }

            exch( a, i, min );
            assert isSorted( a, comparator, 0, i );
        }

        assert isSorted( a, comparator );

    }

    private static boolean less( Comparable v, Comparable w )
    { return v.compareTo( w ) < 0; }

    private static boolean less( Comparator comprator, Object v, Object w )
    { return comprator.compare( v, w ) < 0; }

    private static void exch( Object[] a, int i, int j )
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted( Comparable[] a )
    {
        return isSorted( a, 0, a.length -1 );
    }

    private static boolean isSorted( Comparable[] a, int lo, int hi )
    {
        for( int i = lo + 1; i <= hi; i++ )
            if( less( a[i], a[i-1] ) )
                return false;

        return true;
    }

    private static boolean isSorted( Object[] a, Comparator comparator )
    {
        return isSorted( a, comparator, 0 , a.length - 1);
    }

    private static boolean isSorted( Object[]a, Comparator comparator, int lo, int hi )
    {
        for( int i = lo + 1; i <= hi; i++ )
            if( less( comparator, a[i], a[i-1] ) )
                return false;

        return true;
    }

    private static void show( Comparable[] a )
    {
        for( int i = 0; i < a.length; i++ )
            System.out.println( a[i] );
    }



    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Selection.sort(a);
        show(a);
    }
}
