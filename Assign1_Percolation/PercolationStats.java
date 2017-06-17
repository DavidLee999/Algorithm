import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	// private Percolation pr;
	private double[] f;
	private int size;
	// private int num;
	
	public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
	{
		if (n <= 0)
			throw new IllegalArgumentException("The size must be bigger than 0.");
		if (trials <= 0)
			throw new IllegalArgumentException("The experiment times must be bigger than 9.");
		
		size = n;
		
		// num = trials;
		
		// pr = new Percolation( size );
		
		f = new double[trials];
		
		experiments();
	}
	
	private int randPercolates()
	{
		// int conter = 0;
		Percolation pr = new Percolation(size);
		while (!pr.percolates())
		{
			int p = StdRandom.uniform(1, size + 1);
			int q = StdRandom.uniform(1, size + 1);
			
			if (!pr.isOpen(p, q))
				pr.open( p, q );
		}
		
		return pr.numberOfOpenSites();
	}
	
	private void experiments()
	{
		int counter = 0;
		while (counter < f.length)
		{
			int numOfOpenSites = randPercolates();
			f[counter] = (double)(numOfOpenSites) / (size * size) ;
			
			counter += 1;
		}
	}
	
	public double mean()                          // sample mean of percolation threshold
	{
		return StdStats.mean(f);
	}
	
	public double stddev()                        // sample standard deviation of percolation threshold
	{
		return StdStats.stddev(f);
	}
	
	public double confidenceLo()                  // low  endpoint of 95% confidence interval
	{
		return mean() - 1.96 * stddev() / Math.sqrt(f.length);
	}
	
	public double confidenceHi()                  // high endpoint of 95% confidence interval
	{
		return mean() + 1.96 * stddev() / Math.sqrt(f.length);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		
		PercolationStats PS = new PercolationStats(n, trials);
		
		StdOut.printf("Mean                    = %1.16f%n", PS.mean());
		StdOut.printf("Stddev                  = %1.16f%n", PS.stddev());
		StdOut.printf("95%% confidence interval = [%1.16f %1.16f]%n", PS.confidenceLo(), PS.confidenceHi());
	}

}
