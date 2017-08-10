import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;

public class MaxPQ<Key> implements Iterable<Key> {
	
	private Key[] pq;
	private int n;
	private Comparator<Key> comparator;
	
	public MaxPQ(int initCapacity)
	{
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}
	
	public MaxPQ()
	{
		this(1);
	}
	
	public MaxPQ(int initCapacity, Comparator<Key> comparator)
	{
		this.comparator = comparator;
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}
	
	public MaxPQ(Key[] keys)
	{
		n = keys.length;
		pq = (Key[]) new Object[n + 1];
		
		for (int i = 0; i < n; i++)
			pq[i + 1] = keys[i];
		
		for (int k = n/2; k >= 1; k--)
			sink(k);
		
		assert isMaxHeap();
	}
	
	public boolean isEmpty()
	{ return n == 0; }
	
	public int size()
	{ return n; }
	
	public Key max()
	{
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow!");
		
		return pq[1];
	}
	
	public MaxPQ(Comparator<Key> comparator)
	{
		this(1, comparator);
	}
	
	private void resize(int capacity)
	{
		assert capacity > n;
		
		Key[] temp = (Key[]) new Object[capacity];
		for (int i = 0; i <=n; i++)
			temp[i] = pq[i];
		
		pq = temp;
	}
	
	public void insert(Key x)
	{
		if (n == pq.length - 1)
			resize(2 * pq.length);
		
		pq[++n] = x;
		swim(n);
		assert isMaxHeap();
	}
	
	public Key delMax()
	{
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow!");
		
		Key max = pq[1];
		exch(1, n--);
		sink(1);
		
		pq[n + 1] = null;
		if ((n > 0) && (n == (pq.length - 1) / 4))
			resize(pq.length / 2);
		
		assert isMaxHeap();
		return max;
	}
	
	private void swim(int k)
	{
		while (k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k = k / 2;
		}
	}
	
	private void sink(int k)
	{
		while (2 * k <= n) {
			int j = 2 * k;
			if( j < n && less(j, j + 1))
				j++;
			if(!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}
	
	private boolean less(int i, int j)
	{
		if (comparator == null)
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
		else
			return comparator.compare(pq[i], pq[j]) < 0;
	}
	
	private void exch(int i, int j)
	{
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}
	
	private boolean isMaxHeap()
	{ return isMaxHeap(1); }
	
	private boolean isMaxHeap(int k)
	{
		if (k > n)
			return true;
		int left = 2 * k;
		int right = 2 * k + 1;
		if (left <= n && less(k, left))
			return false;
		if (right <= n &&less(k, right))
			return false;
		
		return isMaxHeap(left) & isMaxHeap(right);
	}
	
	@Override
	public Iterator<Key> iterator()
	{
		// TODO Auto-generated method stub
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<Key> {
		private MaxPQ<Key> copy;
		
		public HeapIterator()
		{
			if (comparator == null)
				copy = new MaxPQ<Key>(n);
			else
				copy = new MaxPQ<Key>(n, comparator);
			for (int i = 1; i <= n; i++)
				copy.insert(pq[i]);
		}

		@Override
		public boolean hasNext()
		{
			// TODO Auto-generated method stub
			return !copy.isEmpty();
		}

		@Override
		public Key next()
		{
			// TODO Auto-generated method stub
			if(!hasNext())
				throw new NoSuchElementException("Iterator out of range!");
			
			return copy.delMax();
		}
		
		@Override
		public void remove()
		{ throw new UnsupportedOperationException(); }
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MaxPQ<String> pq = new MaxPQ<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-"))
				pq.insert(item);
			else if (!pq.isEmpty())
				System.out.print(pq.delMax() + " ");
		}
		System.out.println(pq.size() + " left on pq.");
	}
	

}
