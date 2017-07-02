import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class ResizingArrayQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int n;
    private int first;
    private int last;

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() { return i < n; }

        public Item next()
        {
            Item item = arr[(first + i) % arr.length];
            i++;

            return item;
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }
    private void resize( int capacity )
    {
        Item[] newarr = (Item[]) new Object[capacity];

        for( int i = 0; i < n; ++i )
            newarr[i] = arr[(first + i) % arr.length];

        arr = newarr;
        first = 0;
        last = n;
    }
    public ResizingArrayQueue(){
        arr = (Item[]) new Object[2]; // initialization with length 2
        first = 0;
        last = 0;
        n = 0;
    }

    public boolean isEmpty()
    { return n == 0; }

    public int size()
    { return n; }

    public void enqueue( Item item )
    {
        if( n == arr.length )
            resize( 2*arr.length );

        arr[last++] = item;
        n++;

        if( last == arr.length ) // warp up to use empty spaces
            last = 0;
    }

    public Item dequeue()
    {
        if( isEmpty() )
            throw new IndexOutOfBoundsException("Queue underflow");

        Item item = arr[first];
        arr[first++] = null;
        n--;

        if( first == arr.length ) // warp up to use empty space
            first = 0;

        if( n > 0 && n == arr.length / 4 )
            resize( arr.length / 2 );

        return item;
    }

    public Iterator<Item> iterator()
    {
        return new ArrayIterator();
    }


    public static void main( String[] args )
    {
        ResizingArrayQueue q = new ResizingArrayQueue();

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
