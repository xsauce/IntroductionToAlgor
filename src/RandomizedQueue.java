import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int n;
	private int first;
	private int last;
	private Item[] arr;

	public RandomizedQueue() {
		// construct an empty randomized queue
		n = 0;
		arr = (Item[]) new Object[2];
		first = 0;
		last = 0;
	}

	public boolean isEmpty() {
		// is the queue empty?
		return n == 0;
	}

	public int size() {
		// return the number of items on the queue
		return n;
	}

	private void resize(int size) {
		assert size >= n;
		Item[] temp = (Item[]) new Object[size];
		for (int i = 0; i < n; i++) {
			temp[i] = arr[(first + i) % arr.length];
		}
		arr = temp;
		first = 0;
		last = n;
	}

	public void enqueue(Item item) {
		// add the item
		if (item == null)
			throw new NullPointerException();
		if (n == arr.length)
			resize(2 * arr.length);
		arr[last++] = item;
		if (last == arr.length)
			last = 0;
		n++;
	}

	private void swap(int a, int b) {
		Item temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public Item dequeue() {
		// remove and return a random item
		if (isEmpty())
			throw new NoSuchElementException();
		int randomIndex = (StdRandom.uniform(0, n) + first) % arr.length;
		swap(randomIndex, first);
		Item item = arr[first];
		arr[first] = null;
		n--;
		first++;
		if (first == arr.length)
			first = 0;
		if (n > 0 && n == arr.length / 4)
			resize(arr.length / 2);
		return item;
	}

	public Item sample() {
		// return (but do not remove) a random item
		if (isEmpty())
			throw new NoSuchElementException();
		int randomIndex = (StdRandom.uniform(0, n) + first) % arr.length;
		return arr[randomIndex];
	}

	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new RandomizedIterator<Item>();
	}

	private class RandomizedIterator<Item> implements Iterator<Item> {
		private Item[] tempArray = (Item[]) new Object[n];
		private int count = n;

		public RandomizedIterator() {
			for (int i = 0; i < n; i++) {
				tempArray[i] = (Item) arr[(i + first) % arr.length];
			}
		}

		public boolean hasNext() {
			return count > 0;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		private void swap(int a, int b) {
			Item temp = tempArray[a];
			tempArray[a] = tempArray[b];
			tempArray[b] = temp;
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			int randomIndex = StdRandom.uniform(0, count);
			swap(randomIndex, count - 1);
			Item item = tempArray[count - 1];
			count--;
			return item;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		rq.enqueue("a");
		rq.enqueue("b");
		rq.dequeue();
		rq.enqueue("c");
		rq.enqueue("d");
		rq.enqueue("e");
		rq.dequeue();
		rq.enqueue("e");
		rq.dequeue();
		rq.dequeue();
		rq.dequeue();
		for(String x:rq){
			StdOut.println(x);
		}
	}

}
