import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int n;
    private int last;

    private void resize( int newCapacity )
    {
        Item[] newarr = (Item[]) new Object[newCapacity];

        for( int i = 0; i < n; ++i )
            newarr[i] = arr[i];

        arr = newarr;
        last = n;
    }

    private class RandomizedIterator implements Iterator<Item> {
        private int l = last;
        private int s = n;
        Item[] new_arr = arr.clone();

        public boolean hasNext() { return s > 0; }

        public Item next()
        {
            if( !hasNext() )
                throw new NoSuchElementException();

            int index = StdRandom.uniform(s);

            Item t = arr[index];
            new_arr[index] = new_arr[l-1];

            l--;
            s--;

            return t;
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    public RandomizedQueue()
    {
        arr = (Item[]) new Object[2];
        last = 0;
        n = 0;
    }

    public boolean isEmpty() { return n == 0; }

    public int size() { return n; }

    public void enqueue(Item t)
    {
        if( t == null )
            throw new IllegalArgumentException();

        if( n == arr.length )
            resize( 2*arr.length );

        arr[last++] = t;
        n++;

    }

    public Item dequeue()
    {
        if( isEmpty() )
            throw new NoSuchElementException();

        int index = StdRandom.uniform(n);

        Item t = arr[index];
        arr[index] = arr[last-1];

        arr[--last] = null;
        n--;

        if( n > 0 && n == arr.length / 4 )
            resize( arr.length / 2 );

        return t;
    }

    public Item sample()
    {
        if( isEmpty() )
            throw new NoSuchElementException();

        int index = StdRandom.uniform(n);

        return arr[index];
    }

    @Override
    public Iterator<Item> iterator()
    {
        return new RandomizedIterator();
    }

    public static void main(String[] args) {

    }
}
