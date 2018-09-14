// Kyle Fackrell, Whitney Smith
package Percolation;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

	int experiments;
	double[] openedAverages;

	public PercolationStats(int N, int T) {
		// perform T independent experiments on a grid
		if (N < 0 || T < 0) {
			throw new IllegalArgumentException("Error: Negative number.");
		}
		experiments = T;
		//openedAverages = runExperiment(N, T);
	}

	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(openedAverages);
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(openedAverages);
	}

	public double confidenceLow() {
		// low endpoint of 95% confidence interval
		return mean() - StdStats.stddev(openedAverages);
	}

	public double confidenceHigh() {
		// high endpoint of 95% confidence interval
		return mean() + StdStats.stddev(openedAverages);
	}

	// a main() to test the PercolationStats class
	public static void main(String[] args) {

		PercolationStats test = new PercolationStats(200, 100);

		System.out.println("Results:\n");
		StdOut.println("Mean: " + test.mean());
		StdOut.println("Standard Deviation: " + test.stddev());
		StdOut.println("Confidence Low: " + test.confidenceLow());
		StdOut.println("Confidence High: " + test.confidenceHigh());
	}
}
