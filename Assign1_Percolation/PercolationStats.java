import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	private Percolation pr;
	private double[] f;
	private int size;
	private int num;
	
	public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
	{
		if ( n <= 0 )
			throw new IllegalArgumentException( "The size must be bigger than 0." );
		if ( trials <= 0 )
			throw new IllegalArgumentException( "The experiment times must be bigger than 9." );
		
		size = n;
		
		num = trials;
		
		//pr = new Percolation( size );
		
		f = new double[ num ];
		
		experiments();
	}
	
	private void randPercolates()
	{
		//int conter = 0;
		while ( !pr.percolates() )
		{
			int p = StdRandom.uniform( 0, size );
			int q = StdRandom.uniform( 0, size );
			
			if ( !pr.isOpen( p, q ) )
				pr.open( p, q );
		}
	}
	
	private void experiments()
	{
		int counter = 0;
		while ( counter < num )
		{
			pr = new Percolation( size );
			randPercolates();
			f[ counter ] = (double)( pr.numberOfOpenSites() / ( size * size ) );
			
			counter++;
		}
	}
	
	public double mean()                          // sample mean of percolation threshold
	{
		return StdStats.mean( f );
	}
	
	public double stddev()                        // sample standard deviation of percolation threshold
	{
		return StdStats.stddev( f );
	}
	
	public double confidenceLo()                  // low  endpoint of 95% confidence interval
	{
		return mean() - 1.96 / Math.sqrt( num );
	}
	
	public double confidenceHi()                  // high endpoint of 95% confidence interval
	{
		return mean() + 1.96 / Math.sqrt( num );
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = Integer.parseInt( args[ 0 ] );
		int trials = Integer.parseInt( args[ 1 ] );
		
		PercolationStats PS = new PercolationStats( n, trials );
		
		StdOut.printf( "Mean                    = %f\n", PS.mean() );
		StdOut.printf( "Stddev                  = %f\n", PS.stddev() );
		StdOut.printf( "95% confidence interval = [%f, %f]\n", PS.confidenceLo(), PS.confidenceHi() );
	}

}
