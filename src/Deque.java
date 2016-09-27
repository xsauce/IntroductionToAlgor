import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;
	}
	private Node<Item> first;
	private Node<Item> last;
	private int n;
	
	public Deque() {
		// construct an empty deque
		first = null;
		last = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		// is the deque empty?
		return n == 0;
	}
	
	public int size() {
		// return the number of items on the deque
		return n;
	}

	public void addFirst(Item item) {
		// add the item to the front
		if(item == null){
			throw new NullPointerException();
		}
		if(isEmpty()){
			first = new Node<Item>();
			first.item = item;
			last = first;
		}
		else{
			Node<Item> newFirst = new Node<Item>();
			newFirst.item = item;
			first.prev = newFirst;
			newFirst.next = first;
			first = newFirst;
		}
		n++;
	}

	public void addLast(Item item) {
		// add the item to the end
		if(item == null){
			throw new NullPointerException();
		}
		if(isEmpty()){
			last = new Node<Item>();
			last.item = item;
			first = last;
		}
		else{
			Node<Item> newLast = new Node<Item>();
			newLast.item = item;
			newLast.prev = last;
			last.next = newLast;
			last = newLast;
		}
		n++;
	}

	public Item removeFirst() {
		// remove and return the item from the front
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		Node<Item> oldFirst = first;
		if(size() == 1){
			first = null;
			last = null;
		}
		else{
			oldFirst.next.prev = null;
			first = oldFirst.next;
			oldFirst.next = null;
		}
		n--;
		return oldFirst.item;
	}

	public Item removeLast() {
		// remove and return the item from the end
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		Node<Item> oldLast = last;
		if(size() == 1){
			first = null;
			last = null;
		}
		else{
			oldLast.prev.next = null;
			last = last.prev;
			oldLast.prev = null;
		}
		n--;
		return oldLast.item;
	}

	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new ListIterator<Item>(first);
	}
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> cur;
		public ListIterator(Node<Item> first){
			cur = first;
		}
		public boolean hasNext(){
			return cur != null;
		}
		public Item next(){
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			Item item = cur.item;
			cur = cur.next;
			return item;
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<String> list = new Deque<String>();
		list.addLast("1");
		list.addLast("2");
		list.removeLast();
		list.removeLast();
		StdOut.println(list.isEmpty());
		
	}

}
