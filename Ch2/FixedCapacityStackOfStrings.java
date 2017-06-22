import edu.princeton.cs.algs4.StdIn;


public class FixedCapacityStackOfStrings {
	
	private String[] s;
	private int N;
	
	public FixedCapacityStackOfStrings( int capacity )
	{
		s = new String[capacity];
		N = 0;
	}
	
	public boolean isEmpty()
	{ return N == 0; }
	
	public boolean isFull()
	{ return N == s.length; }
	
	public void push( String item )
	{ s[N++] = item; }
	
	public String pop()
	{ return s[--N]; }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int max = StdIn.readInt();
		FixedCapacityStackOfStrings st = new FixedCapacityStackOfStrings(max);
		
		while( !StdIn.isEmpty() ){
			String s = StdIn.readString();
			
			if ( s.equals( "-" ) ) {
				if ( st.isEmpty() )
					System.out.println( "Empty!" );
				else
					System.out.println( st.pop() );
			}
			else if ( s.equals("?") )
				System.out.println( st.isEmpty() );				
			else
				st.push( s );
		}
	}

}
