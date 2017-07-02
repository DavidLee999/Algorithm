import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class LinkedListStack<Item> implements Iterable<Item> {
	
	private class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator( Node<Item> first )
        {
            current = first;
        }

        public boolean hasNext()
        {
            return current == null;
        }

        public Item next()
        {
            Item item = current.item;
            current = current.next;

            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
	private Node<Item> first;
    private int n;

    public LinkedListStack(){
        first = null;
        n = 0;
    }
	
	public boolean isEmpty()
	{ return first == null; }

    public int size() { return n; }
	
	public void push( Item item )
	{
		Node<Item> oldfirst = first;
		
		first = new Node<Item>();
		
		first.item = item;
		first.next = oldfirst;

        n++;
	}
	
	public Item pop()
	{
        if( isEmpty() )
            throw new IndexOutOfBoundsException("The Stack is Empty!");

		Item item = first.item;
		
		first = first.next;
		
        n--;

		return item;
	}

    public Item peak()
    {
        if( isEmpty() )
            throw new IndexOutOfBoundsException("The Stack is Empty!");

        return first.item;
    }

    public Iterator<Item> iterator()
    {
        return new ListIterator<Item>( first );
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListStack<String> st = new LinkedListStack<String>();
		
		while( !StdIn.isEmpty() ){
			String s = StdIn.readString();
			
			if ( s.equals( "-" ) ) {
				if ( st.isEmpty() )
					System.out.println( "Empty!" );
				else
					System.out.println( st.pop() );
			}
			else
				st.push( s );
		}

        for( String i : st )
            System.out.print( i + " " );

        System.out.print( st.size() );
	}

}
