public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
	
	private Key[] pq;
	private int n;
	
	private boolean less(Key v, Key w)
	{ return v.compareTo(w) < 0; }
	
	public OrderedArrayMaxPQ(int capacity)
	{
		pq = (Key[]) new Comparable[capacity];
		n = 0;
	}
	
	public boolean isEmpty()
	{ return n == 0; }
	
	public int size()
	{ return n; }
	
	public Key delMax()
	{ return pq[--n]; }
	
	public void insert(Key key)
	{
		int i = n - 1;
		while (i >= 0 && less(key, pq[i]))
		{
			pq[i + 1] = pq[i];
			i--;
		}
		
		pq[i+1] = key;
		n++;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);
		
		pq.insert("this");
		pq.insert("is");
		pq.insert("a");
		pq.insert("test");
		
		while (!pq.isEmpty())
			System.out.println(pq.delMax());
	}

}
