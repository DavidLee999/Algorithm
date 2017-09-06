import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionPathCompression {

	private int[] parents;
	private int counter;
	private int[] sz;
	
	public WeightedQuickUnionPathCompression (int n) {
		
		counter = n;
		
		parents = new int[n];
		
		sz = new int[n];
		
		for (int i = 0; i < n; ++i){
			
			parents[i] = i;
			
			sz[i] = 1;
		}
			
	}
	
	public int getCounter () { return counter; }
	
	private void validate ( int p ){
		
		int len = parents.length;
		
		if ( p < 0 || p >= len)
			
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (len-1));
	}
	
	private int findRoot (int p) {
		
		while  ( p != parents[p] ){
			
			parents[p] = parents[parents[p]];
		
			p = parents[p];
		}
		
		return p;
	}
	
	public boolean connected( int p, int q ) {
		
		validate(p);
		validate(q);
		
		return ( findRoot(q) == findRoot(p) );
		
	}
	
	public void union (int p, int q) {
		
		validate(p);
		validate(q);
		
		int rootP = findRoot(p);
		int rootQ = findRoot(q);
		
		if ( rootP == rootQ ) 
			
			return;
		
		if ( sz[rootP] < sz[rootQ] ) {
			
			parents[rootP] = rootQ;
			
			sz[rootQ] += sz[rootP];
		}
		else {
			
			parents[rootQ] = rootP;
			
			sz[rootP] += sz[rootQ];
			
		}
		
		counter--;
			
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = StdIn.readInt();
		
		WeightedQuickUnionPathCompression wqu = new WeightedQuickUnionPathCompression(n);
		
		long startTime=System.currentTimeMillis();
		
		while(!StdIn.isEmpty()){
 
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			
			if ( wqu.connected(p, q) ){
				System.out.println("the " + p + " and " + q + " is not connected.");
				continue;
			}
			
			wqu.union(p, q);
			
			StdOut.println(p + " " + q + " is unioned");
			
		}
		
		long endTime=System.currentTimeMillis(); 
		
		System.out.println("run time: " + (endTime - startTime) + "ms");
		
		StdOut.println("there are " + wqu.getCounter() + " components.");
	}

}
