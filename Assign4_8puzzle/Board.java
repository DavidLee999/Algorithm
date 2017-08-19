import java.util.LinkedList;
import java.util.List;
// import java.util.Stack;

public class Board {

    private final int[][] blocks;
    private final int dim;
    private int blankRow;
    private int blankCol;

    private int[][] copyBlocks(int[][] src)
    {
        int[][] copy = new int[src.length][];
        for (int row = 0; row < src.length; ++row)
            copy[row] = src[row].clone();

        return copy;
    }
    public Board(int[][] blocks)
    {
        if (blocks == null)
            throw new java.lang.IllegalArgumentException();

        dim = blocks.length;
        this.blocks = copyBlocks(blocks);

        for (int row = 0; row < dim; ++row)
        {
            for (int col = 0; col < dim; ++col)
            {
                if (blocks[row][col] == 0)
                {
                    blankRow = row;
                    blankCol = col;
                    return;
                }
            }
        }
    }

    public int dimension()
    { return dim; }

    public int hamming()
    {
        int sum = 0;

        for (int row = 0; row < dim; ++row)
        {
            for (int col = 0; col < dim; ++col)
            {
                if (row == blankRow && col == blankCol)
                    continue;

                if (outOfPlace(row, col) != 0)
                    ++sum;
            }
        }

        return sum;
    }

    private int outOfPlace(int row, int col)
    {
        // value = dim * row + col + 1
        int value = blocks[row][col] - 1;
        int rightRow = value / dim;
        int rightCol = value % dim;

        return Math.abs(rightRow - row) + Math.abs(rightCol - col);
    }

    public int manhattan()
    {
        int sum = 0;

        for (int row = 0; row < dim; ++row)
        {
            for (int col = 0; col < dim; ++col)
            {
                if (row == blankRow && col == blankCol)
                    continue;
                sum += outOfPlace(row, col);

            }
        }

        return sum;
    }

    public boolean isGoal()
    { return hamming() == 0; }

    private void exch(int[][] a, int rowA, int colA, int rowB, int colB)
    {
        int swap = a[rowA][colA];
        a[rowA][colA] = a[rowB][colB];
        a[rowB][colB] = swap;
    }

    public Board twin()
    {
        int[][] copy = copyBlocks(blocks);

        if (blankRow != 0)
            exch(copy, 0, 0, 0, 1);
        else
            exch(copy, 1, 0, 1, 1);

        return new Board(copy);
    }

    public boolean equals(Object y)
    {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (this.getClass() != y.getClass())
            return false;

        Board copy = (Board)y;

        if (this.dim != copy.dim)
            return false;
        if (this.blankCol != copy.blankCol && this.blankRow != copy.blankRow)
            return false;

        for (int i = 0; i < dim; ++i)
            for (int j = 0; j < dim; ++j)
                if(blocks[i][j] != copy.blocks[i][j])
                    return false;

        return true;
    }

    public Iterable<Board> neighbors()
    {
        List<Board> neigh = new LinkedList<Board>();

        if (blankRow > 0) // blank not on the first row
        {
            int[][] upper = copyBlocks(blocks);
            exch(upper, blankRow, blankCol, blankRow - 1, blankCol);
            neigh.add(new Board(upper));
        }

        if (blankRow < dim - 1) // blank not on the last row
        {
            int[][] lower = copyBlocks(blocks);
            exch(lower, blankRow, blankCol, blankRow + 1, blankCol);
            neigh.add(new Board(lower));
        }

        if (blankCol > 0) // blank not on the first column
        {
            int[][] left = copyBlocks(blocks);
            exch(left, blankRow, blankCol, blankRow, blankCol - 1);
            neigh.add(new Board(left));
        }

        if (blankCol < dim - 1) // blank not on the last column
        {
            int[][] right = copyBlocks(blocks);
            exch(right, blankRow, blankCol, blankRow, blankCol + 1);
            neigh.add(new Board(right));
        }

        return neigh;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(dim).append("\n");
        for (int row = 0; row < dim; ++row)
        {
            for (int col = 0; col < dim; ++ col)
                sb.append(String.format("%2d ", blocks[row][col]));
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

    }

}
