import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;
	
	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private boolean color;
		private int size;
		
		public Node(Key key, Value value, boolean color, int size)
		{
			this.key = key;
			this.val = value;
			this.color = color;
			this.size = size;
		}
	}
	
	public RedBlackBST() {}
	
	private boolean isRed(Node x)
	{
		if (x == null)
			return false;
		return x.color == RED;
	}
	
	private int size(Node x)
	{
		if (x == null)
			return 0;
		return x.size;
	}
	
	public int size()
	{
		return size(root);
	}
	
	public boolean isEmpty()
	{ return root == null; }
	
	public Value get(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("Argument to get() is null!");
		
		return get(root, key);
	}
	
	private Value get(Node x, Key key)
	{
		while (x != null) {
			int cmp = key.compareTo(x.key);
			
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				return x.val;
		}
		
		return null;
	}
	
	public boolean contains(Key key)
	{ return get(key) != null; }
	
	public void put(Key key, Value val)
	{
		if (key == null)
			throw new IllegalArgumentException("Argument to put() is null!");
		if (val == null)
		{
			delete(key);
			return;
		}
		
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	private Node put(Node h, Key key, Value val)
	{
		if (h == null)
			return new Node(key, val, RED, 1);
		
		int cmp = key.compareTo(h.key);
		if (cmp < 0)
			h.left = put(h.left, key, val);
		else if (cmp > 0)
			h.right = put(h.right, key, val);
		else
			h.val = val;
		
		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColor(h);
		
		h.size = 1 + size(h.left) + size(h.right);
		
		return h;
	}
	
	public void deleteMin()
	{
		if (isEmpty())
			throw new NoSuchElementException("BST underflow!");
		
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		
		root = deleteMin(root);
		if (!isEmpty())
			root.color = BLACK;
	}
	
	private Node deleteMin(Node h)
	{
		if (h.left == null)
			return null;
		
		if (!isRed(h.left) && !isRed(h.right))
			h = moveRedLeft(h);
		
		h.left = deleteMin(h.left);
		
		return balance(h);
	}
	
	public void deleteMax()
	{
		if (isEmpty())
			throw new NoSuchElementException("BST underflow!");
		
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		
		root = deleteMax(root);
		
		if (!isEmpty())
			root.color = BLACK;
	}

	private Node deleteMax(Node h)
	{
		if (isRed(h.left))
			h = rotateRight(h);
		
		if (h.right == null)
			return null;
		
		if (!isRed(h.right) && !isRed(h.right.left))
			h = moveRedRight(h);
		
		h.right = deleteMax(h.right);
		
		return balance(h);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
