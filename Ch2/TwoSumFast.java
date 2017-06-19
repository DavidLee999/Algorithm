import java.util.Arrays;
import edu.princeton.cs.algs4.In;

public class TwoSumFast {
	public static int count( int [] a )
	{
		int size = a.length;
		
		Arrays.sort( a );
		
		if ( containsDuplicates( a ) ) throw new IllegalArgumentException( "Array contains duplicate integers" );
		
		int count = 0;
		
		BinarySearch bs = new BinarySearch();
		
		for ( int i = 0; i < size; ++i )
		{
			int j = bs.binarySearch( a, -a[i] );
			
			if ( j > i )
				++count;
		}
		
		return count;
		
	}
	
	private static boolean containsDuplicates( int[] a )
	{
		for ( int i = 1; i < a.length; ++i )
			if ( a[i] == a[i - 1] )
				return true;
			
		return false;
	}
	
	public static void main(String[] args)
	{
		In in = new In( args[0] );
		
		int[] arr = in.readAllInts();
		
		int num = count( arr );
		
		System.out.println( num );
	}
}