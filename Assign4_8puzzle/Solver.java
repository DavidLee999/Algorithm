import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
// import java.util.Stack;
import java.util.Deque;
import java.util.LinkedList;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private final SearchNode prev;
        private final Board board;
        private final int move;
        private final int pri;

        public SearchNode(Board b, int m, SearchNode p)
        {
            prev = p;
            board = b;
            move = m;
            pri = board.manhattan() + m;
        }

        public int priority()
        { return pri; }

        public int moves()
        { return move; }

        public Board getBoard()
        { return board; }

        public SearchNode getPreviousNode()
        { return prev; }

        @Override
        public int compareTo(SearchNode that) {
            // TODO Auto-generated method stub
            return this.priority() - that.priority();
        }
    }

    private MinPQ<SearchNode> pq;
    private SearchNode solutionNode;
    private boolean solvable;


    public Solver(Board initial)
    {
        pq = new MinPQ<SearchNode>();
        pq.insert(new SearchNode(initial, 0, null));

        while (true) {
            SearchNode currentNode = pq.delMin();
            Board currentBoard = currentNode.getBoard();

            if (currentBoard.isGoal())
            {
                solutionNode = currentNode;
                solvable = true;
                break;
            }

            if (currentBoard.hamming() == 2 && currentBoard.twin().isGoal())
            {
                solvable = false;
                solutionNode = null;
                break;
            }

            int move = currentNode.moves();
            Board previousBoard = move > 0 ? currentNode.getPreviousNode().getBoard() : null;

            for (Board nextBoard : currentBoard.neighbors())
            {
                if (nextBoard.equals(previousBoard)) // previousBoard != null &&
                    continue;

                pq.insert(new SearchNode(nextBoard, currentNode.moves() + 1, currentNode));
            }

        }


    }

    public boolean isSolvable()
    { return solvable; }

    public int moves()
    { return solvable ? solutionNode.moves() : -1; }

    public Iterable<Board> solution()
    {
        if (!solvable)
            return null;

        Deque<Board> solu = new LinkedList<>();
        SearchNode node = solutionNode;
        while (node != null) {
            solu.addFirst(node.getBoard());
            node = node.getPreviousNode();
        }
        return solu;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        In in = new In(args[0]);

        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else
        {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
