import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int n;
	private boolean[][] openStates;
	private WeightedQuickUnionUF model;
	private WeightedQuickUnionUF noVirtualNodeModel;

	public Percolation(int n) {
		// create n-by-n grid, with all sites blocked
		if (n <= 0)
			throw new IllegalArgumentException();
		this.n = n;
		openStates = new boolean[n][n];
		model = new WeightedQuickUnionUF(n * n + 2);
		noVirtualNodeModel = new WeightedQuickUnionUF(n * n + 1);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				openStates[i][j] = false;
				if (i == 0) {
					model.union(0, toIndex(i + 1, j + 1));
					noVirtualNodeModel.union(0, toIndex(i + 1, j + 1));
				}
				if (i == n - 1){
					model.union(n * n + 1, toIndex(i + 1, j + 1));
				}
			}
		}
	}

	private int toIndex(int i, int j) {
		return (i - 1) * n + j;
	}

	public void open(int i, int j) {
		// open site (row i, column j) if it is not open already
		if ((i > n && i < 1) || (j > n && j < 1))
			throw new IndexOutOfBoundsException();
		if (isOpen(i, j) == false) {
			openStates[i - 1][j - 1] = true;
			// up node
			if (i >= 2 && isOpen(i - 1, j)){
				model.union(toIndex(i - 1, j), toIndex(i, j));
				noVirtualNodeModel.union(toIndex(i - 1, j), toIndex(i, j));
			}
			// down node
			if (i <= n - 1 && isOpen(i + 1, j)){
				model.union(toIndex(i + 1, j), toIndex(i, j));
				noVirtualNodeModel.union(toIndex(i + 1, j), toIndex(i, j));
			}
			// left node
			if (j >= 2 && isOpen(i, j - 1)){
				model.union(toIndex(i, j - 1), toIndex(i, j));
				noVirtualNodeModel.union(toIndex(i, j - 1), toIndex(i, j));
			}
			// right node
			if (j <= n - 1 && isOpen(i, j + 1)){
				model.union(toIndex(i, j + 1), toIndex(i, j));
				noVirtualNodeModel.union(toIndex(i, j + 1), toIndex(i, j));
			}
		}
	}

	public boolean isOpen(int i, int j) {
		// is site (row i, column j) open?
		if ((i > n && i < 1) || (j > n && j < 1))
			throw new IndexOutOfBoundsException();
		return openStates[i - 1][j - 1];
	}

	public boolean isFull(int i, int j) {
		// is site (row i, column j) full?
		if ((i > n && i < 1) || (j > n && j < 1))
			throw new IndexOutOfBoundsException();
		return isOpen(i, j) && noVirtualNodeModel.connected(0, toIndex(i, j));
	}

	public boolean percolates() {
		// does the system percolate?
		if (n == 1){
			return isOpen(1, 1);
		}
		else{
			return model.connected(0, n * n + 1);
		}
	}

	public static void main(String[] args) {
		Percolation p = new Percolation(1);
		StdOut.println(p.percolates());
		p.open(1, 1);
		StdOut.println(p.percolates());
	}
}
