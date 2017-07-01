import edu.princeton.cs.algs4.StdIn;


public class ResizingArrayQueue{

    private String[] arr;
    private int n;
    private int first;
    private int last;

    private void resize( int capacity )
    {
        String[] newarr = new String[capacity];

        for( int i = 0; i < n; ++i )
            newarr[i] = arr[(first + i) % arr.length];

        arr = newarr;
        first = 0;
        last = n;
    }
    public ResizingArrayQueue(){
        arr = new String[2]; // initialization with length 2
        first = 0;
        last = 0;
        n = 0;
    }

    public boolean isEmpty()
    { return n == 0; }

    public int size()
    { return n; }

    public void enqueue( String item )
    {
        if( n == arr.length )
            resize( 2*arr.length );

        arr[last++] = item;
        n++;

        if( last == arr.length ) // warp up to use empty spaces
            last = 0;
    }

    public String dequeue()
    {
        if( isEmpty() )
            throw new IndexOutOfBoundsException("Queue underflow");

        String item = arr[first];
        arr[first++] = null;
        n--;

        if( first == arr.length ) // warp up to use empty space
            first = 0;

        if( n > 0 && n == arr.length / 4 )
            resize( arr.length / 2 );

        return item;
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
