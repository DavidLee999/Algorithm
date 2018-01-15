import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class AdjMatrixGraph {

	private static final String NEWLINE = System.getProperty("line.separator");
	private int V;
	private int E;
	private boolean[][] adj;
	
	
	public AdjMatrixGraph(int V)
	{
		if (V < 0)
			throw new RuntimeException("Number of vertices must be nonnegative");
		
		this.V = V;
		this.E = 0;
		this.adj = new boolean[V][V];
	}
	
	public AdjMatrixGraph(int V, int E)
	{
		this(V);
		if (E < 0)
			throw new RuntimeException("Number of edges must be nonnegative");
		if (E > V * (V - 1) + V)
			throw new RuntimeException("Too many edges");
		
		while (this.E != E)
		{
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			
			addEdge(v, w);
		}
	}
	
	public int V()
	{ return V;}
	
	public int E()
	{ return E; }
	
	public void addEdge(int v, int w)
	{
		if (!adj[v][w])
			E++;
		
		adj[v][w] = true;
		adj[w][v] = true;
	}
	
	public boolean contains(int v, int w)
	{ return adj[v][w]; }
	
	public Iterable<Integer> adj(int v)
	{
		return new AdjIterator(v);
	}
	
	private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
		private int v;
		private int w = 0;
		
		AdjIterator(int v)
		{
			this.v = v;
		}
		
		public Iterator<Integer> iterator()
		{
			return this;
		}
		
		public boolean hasNext()
		{
			while (w < V)
			{
				if (adj[v][w])
					return true;
				
				w++;
			}
			return false;
		}
		
		public Integer next()
		{
			// TODO Auto-generated method stub
			if (!hasNext())
				throw new NoSuchElementException();
			
			return w++;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		
		for (int v = 0; v < V; v++)
		{
			s.append(v + ": ");
			
			for (int w : adj(v))
				s.append(w + " ");
			
			s.append(NEWLINE);
		}
		
		return s.toString();
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int V = Integer.parseInt(args[0]);
		int E = Integer.parseInt(args[1]);
		
		AdjMatrixGraph G = new AdjMatrixGraph(V, E);
		
		System.out.println(G);
	}

}
