import edu.princeton.cs.algs4.StdIn;


public class LinkedStackOfStrings {
	
	private class Node {
		String item;
		Node next;
	}
	
	private Node first = null;
	
	public boolean isEmpty()
	{ return first == null; }
	
	public void push( String item )
	{
		Node oldfirst = first;
		
		first = new Node();
		
		first.item = item;
		first.next = oldfirst;
		
	}
	
	public String pop()
	{
		String item = first.item;
		
		first = first.next;
		
		return item;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedStackOfStrings st = new LinkedStackOfStrings();
		
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
	}

}
