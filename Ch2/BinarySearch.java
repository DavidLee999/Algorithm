import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import java.util.Scanner;

public class BinarySearch{
	public BinarySearch() {}
	
	public int binarySearch( final int[] a, final int t )
	{
		int lo = 0, hi = a.length-1;
		while ( lo <= hi )
		{
			int mid = ( lo + hi ) / 2;
			
			if ( t < a[ mid ] ) hi = mid - 1;
			else if ( t > a[ mid ] ) lo = mid + 1;
			else return mid;
		}
		
		return -1;
	}
	
	public static void main(String [] args){
		
		In in = new In( args[0] );
		
		int[] arr = in.readAllInts();
		
		Arrays.sort( arr );
		
		BinarySearch bs = new BinarySearch();
		
		Scanner sc = new Scanner(System.in); 
		
		while ( sc.hasNextInt() ){
			int t = sc.nextInt();
			
			int index = bs.binarySearch( arr, t );
			if ( index == -1 )
				System.out.printf( "Do not found %d in the array.%n", t );
			else
				System.out.printf( "arr[%d] = %d.%n", index, t );
		}
	}
}