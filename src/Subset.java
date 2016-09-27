import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> queue = new RandomizedQueue<>();
		int count = 0;
		while (k > 0 && !StdIn.isEmpty()) {
			String item = StdIn.readString();
			count++;
			if (queue.size() < k) {
				queue.enqueue(item);
			}
			else{
				int randomIndex = StdRandom.uniform(count);
				if (randomIndex < k) {
					queue.dequeue();
					queue.enqueue(item);
				}
			}
		}
		for (String i : queue) {
			StdOut.println(i);
		}
	}

}