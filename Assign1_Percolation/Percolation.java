
public class Percolation {

	private boolean[][] grid;
	private int size;
	private int top = 0;
	private int bottom;
	private WeightedQuickUnionUF wqu;
	
	
	Percolation(int N)
	{
		if ( N <= 0)
			throw new IllegalArgumentException("The size must be bigger than 0.");
		size = N;
		
		bottom = size * size + 1;
		
		grid = new boolean[size][size];
		
		wqu = new WeightedQuickUnionUF(size * size + 2);
	}
	/*
	private void validate( int p )
	{
		
	}
	
	private int xy2id(int x, int y)
	{
		
		return 0;
	}
	
	public void open(int x, int y)
	{
		
	}
	
	public boolean isOpen(int x, int y)
	{
		
	}
	
	public boolean  isFull(int x, int y)
	{
		
	}
	
	public boolean isPercolated()
	{
		
	}*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		WeightedQuickUnionUF qu = new WeightedQuickUnionUF(n);
		
		System.out.println(qu.count());
		
		Percolation p = new Percolation(-3);
		
		//System.out.println(p.grid[0][1]);
	}

}
