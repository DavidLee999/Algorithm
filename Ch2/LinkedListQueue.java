import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class LinkedListQueue<Item> implements Iterable<Item> {

    private class Node<Item> {
        private Item data;
        private Node<Item> next;
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator( Node<Item> first )
        { current = first; }

        public boolean hasNext()
        { return current != null; }

        public Item next()
        {
            Item item = current.data;
            current = current.next;

            return item;
        }

        public void remove()
        { throw new UnsupportedOperationException(); }
    }

    private Node<Item> first, last;
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

    public void enqueue( Item item )
    {
        Node<Item> oldlast = last;
        last = new Node<Item>();

        last.data = item;
        last.next  = null;

        if( isEmpty() )
            first = last;
        else
            oldlast.next = last;

        n++;
    }

    public Item dequeue()
    {
        Item item = first.data;
        first = first.next;
        n--;

        if( isEmpty() )
            last = null;

        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator( first );
    }


    public static void main( String[] args )
    {
        LinkedListQueue<String> q = new LinkedListQueue<String>();

        while( !StdIn.isEmpty() ) {
            String item = StdIn.readString();

            if( !item.equals("-") )
                q.enqueue(item);
            else if( !q.isEmpty() )
                System.out.print( q.dequeue() + " " );
        }

        for( String i : q )
            System.out.print( i + " " );

        System.out.println("(" + q.size() + " left on queue).");
    }
}
