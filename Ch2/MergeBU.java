import java.util.Comparator;
import edu.princeton.cs.algs4.StdIn;


public class MergeBU {
    private MergeBU() {};

    private static final int CUTOFF = 7;

    private static void exch( Object[] a, int i, int j )
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    private static boolean less( Comparable v, Comparable w )
    { return v.compareTo( w ) < 0; }

    private static boolean isSorted( Comparable[] a, int lo, int hi )
    {
        for( int i = lo + 1; i <= hi; ++i )
            if( less( a[i], a[i - 1] ) )
                return false;

        return true;
    }

    private static boolean isSorted( Comparable[] a )
    {
        return isSorted( a, 0, a.length - 1 );
    }

    private static void merge( Comparable[] a, Comparable[] aux, int lo, int mid, int hi )
    {
        assert isSorted( a, lo, mid );
        assert isSorted( a, mid + 1, hi );

        for( int k = lo; k <= hi; ++k )
            aux[k] = a[k];

        int i = lo, j = mid + 1, k = lo;
        while( k <= hi ) {
            if( i > mid )
                a[k++] = aux[j++];
            else if( j > hi )
                a[k++] = aux[i++];
            else if( less( aux[j], aux[i] ) )
                a[k++] = aux[j++];
            else
                a[k++] = aux[i++];
        }

        assert isSorted( a, lo, hi );
    }

    private static void insertionSort( Comparable[] a, int lo, int hi )
    {
        for( int i = lo + 1; i <= hi; ++i )
        {
            for( int j = i; j > lo && less( a[j], a[j-1] ); --j )
                exch( a, j, j-1 );
        }
    }

    private static void sort( Comparable[] a, Comparable[] aux, int lo, int hi )
    {
        if( hi <= lo + CUTOFF -  1 )
        {
            insertionSort( aux, lo, hi );
            return;
        }

        int mid = lo + ( hi - lo ) / 2;
        sort( aux, a, lo, mid );
        sort( aux, a, mid + 1, hi );

        if( less( a[mid], a[mid+1] ) )
        {
            System.arraycopy( a, lo, aux, lo, hi - lo + 1 );
            return;
        }

        merge( a, aux, lo, mid, hi );
    }

    public static void sort( Comparable[] a )
    {
        Comparable[] aux = a.clone();
        sort( aux, a, 0, a.length - 1 );

        assert isSorted( a );
    }

    private static void show( Comparable[] a )
    {
        for( int i = 0; i < a.length; ++i )
            System.out.println( a[i] );
    }

    public static void main(String[] args)
    {
        String[] a = StdIn.readAllStrings();
        Merge.sort( a );
        show( a );
    }
}
