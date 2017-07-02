import edu.princeton.cs.algs4.StdIn;


public class LinkedListQueue{

    private class Node{
        private String data;
        private Node next;
    }

    private Node first, last;
    private int n;

    public LinkedListQueue(){
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty()
    { return first == null; }

    public int size()
    { return n; }

    public void enqueue( String item )
    {
        Node oldlast = last;
        last = new Node();

        last.data = item;
        last.next  = null;

        if( isEmpty() )
            first = last;
        else
            oldlast.next = last;

        n++;
    }

    public String dequeue()
    {
        String item = first.data;
        first = first.next;
        n--;

        if( isEmpty() )
            last = null;

        return item;
    }


    public static void main( String[] args )
    {
        LinkedListQueue q = new LinkedListQueue();

        while( !StdIn.isEmpty() ) {
            String item = StdIn.readString();

            if( !item.equals("-") )
                q.enqueue(item);
            else if( !q.isEmpty() )
                System.out.print( q.dequeue() + " " );
        }

        System.out.println("(" + q.size() + " left on queue).");
    }
}
