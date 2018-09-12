// Kyle Fackrell, Whitney Smith

package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	WeightedQuickUnionUF UF, UF2;
	boolean[][] site;
	int N, n, gridSize, topRow, bottomRow;
	
	// create N­by­N grid, with all sites blocked
	public Percolation(int N) {
		
		if (n < 0) {
			throw new IllegalArgumentException("N cannot be negative or 0");
		}
		
		n = N;
		gridSize = n * n;
		site = new boolean[n][n];
		topRow = gridSize;
		bottomRow = gridSize + 1;
		
		UF = new WeightedQuickUnionUF(gridSize + 2);
		
		
//		for (int i = 0; i < site.length; i++) {
//			for (int j = 0; j < site.length; j++) {
//				site[i][j] = false;
//				
//			}
//		}
		
		// Connects the sites on the top and bottom to "topRow" and "bottomRow"
		for (int i = 0; i < n; i++){
		    UF.union(i, topRow);
		    UF.union(gridSize - i - 1, bottomRow);
		}
	}
	
	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
		
		if (i < 0 || i >= n || j < 0 || j >= n) {
			throw new IndexOutOfBoundsException("i or j is not between 0 and " + (n-1));
		}
		
		site[i][j] = true;
		
		// Checks to see if adjacent squares are open and connects them.
		
		if (i + 1 < n && isOpen(i + 1, j) == true) {
			UF.union(xyTo1D(i + 1, j), xyTo1D(i, j));
		}
		
		if (i - 1 >= 0 && isOpen(i - 1, j) == true) {
			UF.union(xyTo1D(i - 1, j), xyTo1D(i, j));
		}
		
		if (j + 1 < n && isOpen(i, j + 1) == true) {
			UF.union(xyTo1D(i, j + 1), xyTo1D(i, j));
		}
		
		if (j - 1 >= 0 && isOpen(i, j - 1) == true) {
			UF.union(xyTo1D(i, j - 1), xyTo1D(i, j));
		}
	}
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		
		if (i < 0 || i >= n || j < 0 || j >= n) {
			throw new IndexOutOfBoundsException("i or j is not between 0 and " + (n-1));
		}
		
		return site[i][j];
	}
	
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		
		if (i < 0 || i >= n || j < 0 || j >= n) {
			throw new IndexOutOfBoundsException("i or j is not between 0 and " + (n-1));
		}
		
		if (isOpen(i, j) == true) {
			return UF.connected(topRow, xyTo1D(i, j));
	    } else {
			return false;
		}
		
	}
	
	// does the system percolate?
	public boolean percolates() {

		return UF.connected(topRow, bottomRow);
	}
	
	private int xyTo1D(int i, int j) {
		
		return (i * n) + j;
	}
}
