import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class Quick_Find {

	private int[] id;
	private int count;
	
	public Quick_Find(int N)
	{
		id = new int[N];
		count = N;
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	private void validate(int p) 
	{
        int n = id.length;
        
        if (p < 0 || p >= n) 
        {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
        }
    }
	
	public boolean connected(int p, int q)
	{
		validate(p);
		validate(q);
		
		return id[p] == id[q];
	}
	
	public void union(int p, int q)
	{
		validate(p);
		validate(q);
		
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid)
				id[i] = qid;
	}
	
	public int count()
	{
		return count;
	}
	
	public static void main(String[] args)
	{
		  int n = StdIn.readInt();
		  Quick_Find qf = new Quick_Find(n);
		  
		  while (!StdIn.isEmpty())
		  {
			  int p = StdIn.readInt();
			  int q = StdIn.readInt();
			  
			  if(qf.connected(p, q))
				  continue;
			  qf.union(p, q);
			  
			  StdOut.println(p + " " + q);
		  }
		  StdOut.println(qf.count() + " components.");
	}
}
