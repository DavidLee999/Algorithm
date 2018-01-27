import edu.princeton.cs.algs4.GraphGenerator;

public class Bridge {
	private int bridges;
	private int cnt;
	private int[] pre;
	private int[] low;
	
	public Bridge(edu.princeton.cs.algs4.Graph g)
	{
		low = new int[g.V()];
		pre = new int[g.V()];
		
		for (int v = 0; v < g.V(); v++)
		{
			low[v] = -1;
			pre[v] = -1;
		}
		
		for (int v = 0; v < g.V(); v++)
			if (pre[v] == -1)
				dfs(g, v, v);
		
	}
	
	public int components()
	{ return bridges + 1; }
	
	private void dfs(edu.princeton.cs.algs4.Graph g, int u, int v)
	{
		pre[v] = cnt++;
		low[v] = pre[v];
		
		for (int w : g.adj(v))
		{
			if (pre[w] == -1)
			{
				dfs(g, v, w);
				low[v] = Math.min(low[v], low[w]);
				
				if (low[w] == pre[w])
				{
					System.out.println(v + " - " + w + " is a bridge");
					bridges++;
				}
			}
			else if (w != u)
				low[v] = Math.min(low[v], pre[w]);
		}
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int V = Integer.parseInt(args[0]);
		int E = Integer.parseInt(args[1]);
		
		edu.princeton.cs.algs4.Graph G = GraphGenerator.simple(V, E);
		System.out.println(G);
		
		Bridge bridge = new Bridge(G);
		System.out.println("Edge connected components = " + bridge.components());
	}

}
