import java.util.Comparator;

public class Shell {
    private Shell() {}

    private static boolean less( Comparable v, Comparable w )
    {
        return v.compareTo( w ) < 0;
    }

    private static void exch( Object[] a, int i, int j )
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted( Comparable[] a )
    {
        for( int i = 1; i < a.length; ++i )
            if( less( a[i], a[i-1] ) )
                return false;

        return true;
    }


    private static boolean isHSorted( Comparable[] a, int h )
    {
        for( int i = h; i < a.length; ++i )
            if( less( a[i], a[i-h] ) )
                return false;

        return true;
    }

    public static void sort( Comparable[] a )
    {
        int N = a.length;

        int h = 1;
        while( h < N / 3 )
            h = 3 * h + 1;

        while( h >= 1) {
            for( int i = h; i < N; ++i )
            {
                for( int j = i; j >= h && less( a[j], a[j-h] ); j -= h )
                    exch( a, j, j-h );
            }
            assert isHSorted( a, h );
            h /= 3;
        }
        assert isSorted( a );
    }

    public static void show( Comparable[] a )
    {
        for( int i = 0; i < a.length; ++i )
            System.out.print( a[i] + " " );
    }

    public static void main(String[] args)
    {
        Integer[] a = new Integer[] {9,8,7,6,5,4,3,3,1};
        Shell.sort( a );
        show( a );
    }
}
