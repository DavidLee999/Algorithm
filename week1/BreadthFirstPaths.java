import edu.princeton.cs.algs4.In; 
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstPaths(Graph G, int s)
    {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];

        validateVertex(s);

        bfs(G, s);

        assert check(G, s);
    }

    public BreadthFirstPaths(Graph G, Iterable<Integer> sources)
    {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;

        validateVertices(sources);

        bfs(G, sources);
    }

    private void bfs(Graph G, int s)
    {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;

        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v))
            {
                if (!marked[w])
                {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    private void bfs(Graph G, Iterable<Integer> sources)
    {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources)
        {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v))
            {
                if (!marked[w])
                {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v)
    {
        validateVertex(v);
        return marked[v];
    }

    public int distTo(int v)
    {
        validateVertex(v);
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v)
    {
        validateVertex(v);
        if (!hasPathTo(v))
            return null;

        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);

        path.push(x);

        return path;
    }

    private boolean check(Graph G, int s)
    {
        if (distTo[s] != 0)
        {
            System.out.println("Distance of source " + s + " to itself = " + distTo[s]);
            return false;
        }

        for (int v = 0; v < G.V(); v++)
        {
            for (int w : G.adj(v))
            {
                if (hasPathTo(v) != hasPathTo(w))
                {
                    System.out.println("edge " + v + " - " + w);
                    System.out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }
                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1))
                {
                    System.out.println("edge " + v + " - " + w);
                    System.out.println("distTo(" + v + ") = " + distTo(v));
                    System.out.println("distTo(" + w + ") = " + distTo(w));
                    return false;
                }
            }
        }

        for (int w = 0; w < G.V(); w++)
        {
            if (!hasPathTo(w) || w == s)
                continue;

            int v = edgeTo[w];
            if (distTo[w] != distTo[v] + 1)
            {
                System.out.println("shortest path edge " + v + " - " + w);
                System.out.println("distTo[" + v + "] = " + distTo[v]);
                System.out.println("distTo[" + w + "] = " + distTo[w]);
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

    private void validateVertices(Iterable<Integer> vertices)
    {
        if (vertices == null)
        {
            throw new IllegalArgumentException("argument is null");
        }

        int V = marked.length;
        for (int v : vertices)
        {
            if (v < 0 || v >= V)
            {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        In in = new In(args[0]);
        Graph G = new Graph(in);

        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++)
        {
            if (bfs.hasPathTo(v))
            {
                System.out.printf("%d to %d (%d): ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v))
                {
                    if (x == s)
                        System.out.print(x);
                    else
                        System.out.print(" - " + x);
                }
                System.out.println();
            }
            else
                System.out.printf("%d to %d (-): not connected.\n", s, v);
        }
    }

}
