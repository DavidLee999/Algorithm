import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

public class LinearProbingHashST<Key, Value> {
	
	private static final int INIT_CAPACITY = 4;
	
	private int n;
	private int m;
	private Key[] keys;
	private Value[] vals;
	
	private int hash(Key key)
	{ return (key.hashCode() & 0x7fffffff) % m; }
	
	private void resize(int capacity)
	{
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
		
		for (int i = 0; i < m; i++)
			if (keys[i] != null)
				temp.put(keys[i], vals[i]);
		
		keys = temp.keys;
		vals = temp.vals;
		m = temp.m;
	}
	
	public LinearProbingHashST()
	{ this(INIT_CAPACITY); }
	
	public LinearProbingHashST(int capacity)
	{
		m = capacity;
		n = 0;
		keys = (Key[]) new Object[m];
		vals = (Value[]) new Object[m];
	}
	
	public int size()
	{ return n; }
	
	public boolean isEmpty()
	{ return n == 0; }
	
	public boolean contains(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("Argument to contains() is null!");
		
		return get(key) != null;
	}
	
	public Value get(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("Argument to get() is null!");
		
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
			if (keys[i].equals(key))
				return vals[i];
		
		return null;
	}
	
	public void put(Key key, Value val)
	{
		if (key == null)
			throw new IllegalArgumentException("Argument to put() is null!");
		if (val == null)
		{
			delete(key);
			return;
		}
		
		if (n >= m / 2)
			resize(2 * m);
		
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % m)
		{
			if (keys[i].equals(key))
			{
				vals[i] = val;
				return;
			}
		}
		
		keys[i] = key;
		vals[i] = val;
		n++;
	}
	
	public void delete(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("Argument to delete() is null!");
		if (!contains(key))
			return;
		
		int i = hash(key);
		while (!keys[i].equals(key))
			i = (i + 1) % m;
		
		keys[i] = null;
		vals[i] = null;
		
		i = (i + 1) % m;
		while (keys[i] != null) {
			Key keyToRehash = keys[i];
			Value valToRehash = vals[i];
			
			keys[i] = null;
			vals[i] = null;
			n--;
			
			put(keyToRehash, valToRehash);
			
			i = (i + 1) % m;
		}
		
		n--;
		
		if (n > 0 && n <= m / 8)
			resize(m / 2);
		
		assert check();
	}
	
	private boolean check()
	{
		if (m < 2 * n)
		{
			System.err.println("Hash table size m = " + m + "; array size n = " + n);
			return false;
		}
		
		for (int i = 0; i < m; i++)
		{
			if (keys[i] == null)
				continue;
			else if (get(keys[i]) != vals[i])
			{
				System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
				return false;
			}
		}
		
		return true;
	}
	
	public Iterable<Key> keys()
	{
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < m; i++)
			if (keys[i] != null)
				queue.enqueue(keys[i]);
		
		return queue;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
		
		for (int i = 0; !StdIn.isEmpty(); i++)
		{
			String key = StdIn.readString();
			st.put(key, i);
		}
		
		for (String s : st.keys())
			System.out.println(s + " " + st.get(s));

	}

}
