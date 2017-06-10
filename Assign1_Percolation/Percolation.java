
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
	private boolean validate( int p )
	{
		if ( p < 1 || p > size)
			return false;
			//throw new IndexOutOfBoundsException("index " + p + " is not between 1 and " + size);
		else
			return true;
	}
	
	private int xy2id(int row, int col)
	{
		int id = size * (row - 1) + col;
		
		return id;
	}
	
	public void open(int row, int col)
	{
		if ( !validate(row) || !validate(col) )
			throw new IndexOutOfBoundsException("the index is not between 1 and " + size);
		
		int id = xy2id(row, col);
		
		grid[row-1][col-1] = true;
		
		if ( row == 1 )
			wqu.union(id, top);
		
		if ( row == size )
			wqu.union(id, top);
		
		if ( validate(row-1) && isOpen(row-1, col) )
			wqu.union( id, xy2id(row-1, col) );
		
		if ( validate(row+1) && isOpen(row+1, col) )
			wqu.union( id, xy2id(row+1, col) );
		
		if ( validate(col-1) && isOpen(row, col-1) )
			wqu.union( id, xy2id(row, col-1) );
		
		if ( validate(col+1) && isOpen(row, col+1) )
			wqu.union( id, xy2id(row, col+1) );
	}
	
	public boolean isOpen(int row, int col)
	{
		if ( !validate(row) || !validate(col) )
			throw new IndexOutOfBoundsException("the index is not between 1 and " + size);
		
		return grid[row-1][col-1];
	}
	
	public boolean  isFull(int row, int col)
	{
		if ( !validate(row) || !validate(col) )
			throw new IndexOutOfBoundsException("the index is not between 1 and " + size);
		
		return wqu.connected( top, xy2id(row, col) );
	}
	
	public boolean percolates()
	{
		return wqu.connected(top, bottom);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In("E:\\programmingExercise\\algorithm\\Java\\Ch1\\Percolations\\test_files\\wayne98.txt");
		
		int n = in.readInt();
		Percolation uf = new Percolation(n);
		
		long startTime=System.currentTimeMillis();
		
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.isOpen(p, q)) continue;
            uf.open(p, q);
            StdOut.println(p + " " + q);
        }
		
        System.out.println(uf.isPercolated());
        long endTime=System.currentTimeMillis(); 
		
        System.out.println(uf.percolates());
	}

}