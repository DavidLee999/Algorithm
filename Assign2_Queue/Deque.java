import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    private int n;
    private Node<Item> first;
    private Node<Item> last;
    
    private class ListIterator implements Iterator<Item>{
    	
    	private Node<Item> current;
    	
    	public ListIterator(Node<Item> t)
    	{
    		current = t;
    	}
    	
		@Override
		public boolean hasNext() { return current.next != last; }

		@Override
		public Item next() {
			if( !hasNext() )
				throw new NoSuchElementException();
			
			current = current.next;
			return current.item;
		}
		
		@Override
		public void remove() { throw new UnsupportedOperationException(); }
    	
    }

    public Deque()
    {
        n = 0;
        first = new Node<Item>();
        last = new Node<Item>();
        
        first.next = last;
        first.prev = null;
        
        last.prev = first;
        last.next = null;
    }

    public boolean isEmpty() { return n == 0; }

    public int size() { return n; }

    public void addFirst(Item t)
    {
    	if( t == null )
    		throw new IllegalArgumentException();
    	
        Node<Item> new_node = new Node<Item>();

        new_node.item = t;
        new_node.prev = first;
        new_node.next = first.next;
        
        first.next = new_node;
        new_node.next.prev = new_node;
        
        n++;
    }

    public void addLast(Item t)
    {
    	if( t == null )
    		throw new IllegalArgumentException();
    	
        Node<Item> new_node = new Node<Item>();

        new_node.item = t;
        new_node.next = last;
        new_node.prev = last.prev;

        last.prev = new_node;
        new_node.prev.next = new_node;

        n++;
    }

    public Item removeFirst()
    {
    	if ( n == 0 )
    		throw new NoSuchElementException();
    	
        Node<Item> oldfirst = first.next;

        first.next = oldfirst.next;
        oldfirst.next.prev = first;
        n--;

        return oldfirst.item;
    }

    public Item removeLast()
    {
    	if ( n == 0 )
    		throw new NoSuchElementException();
    	
        Node<Item> oldlast = last.prev;

        last.prev = oldlast.prev;
        oldlast.prev.next = last;
        n--;

        return oldlast.item;
    }

    public Iterator<Item> iterator()
    {
    	return new ListIterator(first);
    }
    
    
    public static void main(String[] args)
    {

    }
}