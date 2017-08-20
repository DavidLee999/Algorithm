import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int size;
    private int top;
    private int bottom;
    private int counter;
    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF no_bottom_qu;

    public Percolation(int N)
    {
        if (N <= 0)
            throw new IllegalArgumentException("The size must be bigger than 0.");

        size = N;

        top = 0;

        bottom = size * size + 1;

        grid = new boolean[size][size];

        counter = 0;

        wqu = new WeightedQuickUnionUF(size * size + 2);
        no_bottom_qu = new WeightedQuickUnionUF(size * size + 1);
    }

    private boolean validate(int p)
    {
        if (p < 1 || p > size)
            return false;
        // throw new IndexOutOfBoundsException("index " + p + " is not between 1 and " + size);
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
        if (!validate(row) || !validate(col))
            throw new IndexOutOfBoundsException("the index is not between 1 and " + size);

        if (isOpen(row, col))
            return;

        int id = xy2id(row, col);

        grid[row-1][col-1] = true;

        counter += 1;

        if (row == 1){
            wqu.union(id, top);
            no_bottom_qu.union(id, top);
        }

        if (row == size)
            wqu.union(id, bottom);

        if (validate(row-1) && isOpen(row-1, col)){
            wqu.union(id, xy2id(row-1, col));
            no_bottom_qu.union(id, xy2id(row-1, col));
        }

        if (validate(row+1) && isOpen(row+1, col)){
            wqu.union(id, xy2id(row+1, col));
            no_bottom_qu.union( id, xy2id(row+1, col));
        }

        if (validate(col-1) && isOpen(row, col-1)){
            wqu.union( id, xy2id(row, col-1) );
            no_bottom_qu.union( id, xy2id(row, col-1));
        }

        if (validate(col+1) && isOpen(row, col+1)){
            wqu.union(id, xy2id(row, col+1) );
            no_bottom_qu.union(id, xy2id(row, col+1));
        }
    }

    public boolean isOpen(int row, int col)
    {
        if (!validate(row) ||!validate(col))
            throw new IndexOutOfBoundsException("the index is not between 1 and " + size);

        return grid[row-1][col-1];
    }

    public boolean  isFull(int row, int col)
    {
        if (!validate(row) || !validate(col))
            throw new IndexOutOfBoundsException("the index is not between 1 and " + size);

        return no_bottom_qu.connected(top, xy2id(row, col));
    }

    public int numberOfOpenSites() { return counter; }

    public boolean percolates()
    {
        return wqu.connected(top, bottom);
    }

    public static void main(String[] args) {

    }

}
