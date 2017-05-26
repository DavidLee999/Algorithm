import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnion {
	
	private int[] parents;
	private int counter;
	
	public QuickUnion (int n){
		counter = n;
		parents = new int[n];
		
		for (int i = 0; i < n; ++i){
			parents[i] = i;
		}
	}
	
	public int getCounter(){
		return counter;
	}
	
	private void validate(int p){
		
		int len = parents.length;
		
		if ( p < 0 || p >= len)
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (len-1));
	}
	
	private int findRoot(int p){
		
		while(p != parents[p]){
			p = parents[p];
		}
		
		return p;
	}
	
	public boolean connected(int p, int q){
		
		validate(p);
		validate(q);
		
		return ( findRoot(p) == findRoot(q) );
	}
	
	public void union(int p, int q){
		
		validate(p);
		validate(q);
		
		if( findRoot(p) != findRoot(q) ){
			parents[findRoot(p)] = findRoot(q);		
			--counter;
		}
		else
			return;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int N = StdIn.readInt();
		
		QuickUnion uq = new QuickUnion(N);
		
		while(!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			
			if(uq.connected(p, q)){
				System.out.println(p + " and " + q + " is already connected.");
				continue;
			}
			
			uq.union(p, q);
			
			StdOut.println("now, " + p + " and " + q + " is connected.");
		}
		
		StdOut.println("There are " + qf.count() + " components.");

	}

}