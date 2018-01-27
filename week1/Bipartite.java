import edu.princeton.cs.algs4.GraphGenerator;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

public class Bipartite {
	private boolean isBipartite;
	private boolean[] color;
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	
	public Bipartite(edu.princeton.cs.algs4.Graph g)
	{
		isBipartite = true;
		color = new boolean[g.V()];
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		
		for (int v = 0;  v < g.V(); v++)
			if (!marked[v])
				dfs(g, v);
		
		assert check(g);
	}
	
	private void dfs(edu.princeton.cs.algs4.Graph g, int v)
	{
		marked[v] = true;
		
		for (int w : g.adj(v))
		{
			if (cycle != null)
				return;
			
			if (!marked[w])
			{
				edgeTo[w] = v;
				color[w] = !color[v];
				
				dfs(g, w);
			}
			
			else if (color[w] == color[v])
			{
				isBipartite = false;
				cycle = new Stack<Integer>();
				cycle.push(w);
				
				for (int x = v; x != w; x = edgeTo[x])
					cycle.push(x);
				
				cycle.push(w);
			}
		}
	}
	
	public boolean isBipartite()
	{ return isBipartite; }
	
	public boolean color(int v)
	{
		validateVertex(v);
		if (!isBipartite)
			throw new UnsupportedOperationException("graph is not bipartite");
		
		return color[v];
	}
	
	public Iterable<Integer> oddCycle()
	{ return cycle; }
	
	private boolean check(edu.princeton.cs.algs4.Graph g)
	{
		if (isBipartite)
			for (int v = 0; v < g.V(); v++)
				for (int w : g.adj(v))
					if (color[v] == color[w])
					{
						System.err.printf("edge %d-%d with %d and %d in same side of bipartition\n", v, w, v, w);
						return false;
					}
		else
		{
			int first = -1, last = -1;
			for (int u : oddCycle())
			{
				if (first == -1)
					first = u;
				last = u;
			}
			if ( first != last)
			{
				System.err.printf("cycle begins with %d and ends with %d\n", first, last);
				return false;
			}
		}
		
		return true;
	}
	
	private void validateVertex(int v)
	{
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int V1 = Integer.parseInt(args[0]);
		int V2 = Integer.parseInt(args[1]);
		int E = Integer.parseInt(args[2]);
		int F = Integer.parseInt(args[3]);
		
		edu.princeton.cs.algs4.Graph G = GraphGenerator.bipartite(V1, V2, E);
		
		for (int i = 0; i < F; i++)
		{
			int v = StdRandom.uniform(V1 + V2);
			int w = StdRandom.uniform(V1 + V2);
			
			G.addEdge(v, w);
		}
		
		System.out.println(G);
		
		Bipartite b = new Bipartite(G);
		if (b.isBipartite())
		{
			System.out.println("Graph is bipartite");
			
			for (int v = 0; v < G.V(); v++)
				System.out.println(v + ": " + b.color(v));
		}
		else
		{
			System.out.println("Graph has an odd-length cycle: ");
			
			for (int x : b.oddCycle())
				System.out.print(x +  " ");
			
			System.out.println();
		}
	}

}
