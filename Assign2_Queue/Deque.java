import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable {

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;

    }

    private int size;
    private Node<Item> first;
    private Node<Item> last;

    public Deque()
    {
        size = 0;
        first = new Node<Item>();
        first.next = last;
        first.prev = null;
        last = new Node<Item>();
        last.prev = first;
        last.next = null;
    }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    public void addFirst(Item t)
    {
        Node<Item> new_node = new Node<Item>();

        new_node.item = t;
        new_node.prev = first;
        new_node.next = first.next;

        first.next = new_node.next.prev = new_node;

        size++;
    }

    public void addLast(Item t)
    {
        Node<Item> new_node = new Node<Item>();

        new_node.item = t;
        new_node.next = last;
        new_node.prev = last.prev;

        last.prev = new_node.prev.next = new_node;

        size++;
    }

    public Item removeFirst()
    {
        Node<Item> oldfirst = first.next;

        first.next = oldfirst.next;
        oldfirst.next.prev = first;
        size--;

        Item t = oldfirst.item;
        return t;
    }

    public Item removeLast()
    {
        Node<Item> oldlast = last.prev;

        last.prev = oldlast.prev;
        oldlast.prev.next = last;
        size--;

        Item t = oldlast.item;
        return t;
    }

    public Iterator<Item> iterator()
    {}

    public static void main(String[] args)
    {}
}
