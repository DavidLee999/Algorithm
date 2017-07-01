import edu.princeton.cs.algs4.StdIn;

public class ResizingArrayStackOfStrings {
	private String[] s;
	private int N = 0;
	
	private void resize( int length )
	{
		String[] new_s = new String[length];
		
		for ( int i = 0; i < N; i++ )
			new_s[i] = s[i];
		
		s = new_s;
	}
	public ResizingArrayStackOfStrings()
	{
		s = new String[1];
	}
	
	public boolean isEmpty() { return N == 0; }
	
	public boolean isFull() { return N == s.length; }
	
	public int size() { return s.length; }
	
	public void push( String item )
	{
		if ( N == s.length )
			resize( 2 * s.length );
		
		s[N++] = item;
	}
	
	public String pop()
	{
		String item = s[--N];
		s[N] = null;
		
		if ( N > 0 && N == s.length/4 )
			resize( s.length / 2 );
		
		return item;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResizingArrayStackOfStrings st = new ResizingArrayStackOfStrings();
		
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
			
			System.out.println( st.size() );
		}
	}
}
