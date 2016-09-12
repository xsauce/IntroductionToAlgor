import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double[] resultList;

	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException();

		int randX, randY, openTimes;
		resultList = new double[trials];
		for (int i = 0; i < trials; i++) {
			Percolation p = new Percolation(n);
			openTimes = 0;
			while (p.percolates() == false) {
				randX = StdRandom.uniform(1, n + 1);
				randY = StdRandom.uniform(1, n + 1);
				if (p.isOpen(randX, randY) == false) {
					p.open(randX, randY);
					openTimes++;
				}

			}
			resultList[i] = 1.0 * openTimes / (n * n);
		}
	}

	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(resultList);
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(resultList);
	}

	public double confidenceLo() {
		// low endpoint of 95% confidence interval
		return mean() - (1.96 * stddev() / Math.sqrt(resultList.length));
	}

	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean() + (1.96 * stddev() / Math.sqrt(resultList.length));
	}

	public static void main(String[] args) {
		// test client (described below)
		int n = Integer.parseInt(args[0]);
		int trails = Integer.parseInt(args[1]);
		StdOut.printf("n=%d, trails=%d\n", n, trails);
		PercolationStats pstats = new PercolationStats(n, trails);
		StdOut.printf("mean                    = %f\n", pstats.mean());
		StdOut.printf("stddev                  = %f\n", pstats.stddev());
		StdOut.printf("95%% confidence interval = %f, %f\n",
				pstats.confidenceLo(), pstats.confidenceHi());
	}
}
