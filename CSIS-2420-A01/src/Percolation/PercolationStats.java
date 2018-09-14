// Kyle Fackrell, Whitney Smith
package Percolation;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

	private int experiments;
	private double[] openedAverages;

	public PercolationStats(int N, int T) {
		// perform T independent experiments on a grid
		if (N < 0 || T < 0) {
			throw new IllegalArgumentException("Error: Negative number.");
		}
		experiments = T;
		openedAverages = new double[T];

		// A loop that runs through T experiments and tracks results
		for (int i = 0; i < experiments; i++) {
			Percolation experiment = new Percolation(N);
			int openSites = 0;

			while (!experiment.percolates()) {
				int random = StdRandom.uniform(N);
				int secondRandom = StdRandom.uniform(N);
				if (!experiment.isOpen(random, secondRandom)) {
					experiment.open(random, secondRandom);
					openSites += 1;
				}
			}
			// Record results of an experiment
			openedAverages[i] = ((double)openSites) / (N * N);
		}
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
		return (mean() - ((1.96 * stddev()) / Math.sqrt(experiments)));
	}

	public double confidenceHigh() {
		// high endpoint of 95% confidence interval
		return (mean() + ((1.96 * stddev()) / Math.sqrt(experiments)));
	}

	// a main() to test the PercolationStats class
	public static void main(String[] args) {

		PercolationStats test = new PercolationStats(2, 1000);

		StdOut.println("----------Results----------");
		StdOut.println("Mean: " + test.mean());
		StdOut.println("Standard Deviation: " + test.stddev());
		StdOut.println("Confidence Low: " + test.confidenceLow());
		StdOut.println("Confidence High: " + test.confidenceHigh());
	}
}
