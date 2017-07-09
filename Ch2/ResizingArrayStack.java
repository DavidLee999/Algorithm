import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.lang.RuntimeException;

public class ResizingArrayStack<Item> implements Iterable<Item>  {
    private Item[] s;
    private int N = 0;

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = N - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if( !hasNext() )
                throw new IndexOutOfBoundsException( "Index out of bound!" );

            return s[i--];
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    private void resize( int length )
    {
        Item[] new_s = (Item[]) new Object[length];

        for ( int i = 0; i < N; i++ )
            new_s[i] = s[i];

        s = new_s;
    }
    public ResizingArrayStack()
    {
        s = (Item[]) new Object[2];
    }

    public boolean isEmpty() { return N == 0; }

    public boolean isFull() { return N == s.length; }

    public int size() { return N; }

    public void push( Item item )
    {
        if ( N == s.length )
            resize( 2 * s.length );

        s[N++] = item;
    }

    public Item pop()
    {
        if( isEmpty() )
            throw new IndexOutOfBoundsException("The Stack is empty!");
        Item item = s[--N];
        s[N] = null;

        if ( N > 0 && N == s.length/4 )
            resize( s.length / 2 );

        return item;
    }

    public Item peek() {
        if( isEmpty() )
            throw new IndexOutOfBoundsException( "Index out of bound!" );

        return s[N-1];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ResizingArrayStack<Integer> st = new ResizingArrayStack<Integer>();

        while( !StdIn.isEmpty() ){
            int s = StdIn.readInt();

            if ( s == 0 ) {
                if ( st.isEmpty() )
                    System.out.println( "Empty!" );
                else
                    System.out.println( st.pop() );
            }
            else
                st.push( s );
        }
        System.out.println( st.size() );

        for( int i : st )
            System.out.print(i+" ");
    }
}
