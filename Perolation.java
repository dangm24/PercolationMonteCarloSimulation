import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][]grid;
	private int size;
	private WeightedQuickUnionUF perc;
	private int virtualTop;
	private int virtualBottom;

    public Percolation (int n) {
    	this.size = n;
    	this.virtualTop = 0;
    	this.virtualBottom = n*n + 1;
    	this.perc = new WeightedQuickUnionUF(n*n + 2);
    	this.grid = new boolean[n][n];
   	
    }
	
    public void open(int i, int j) {
	    if ((i < 1 || i > this.size) || (j < 1 || j > this.size)) {
	    	throw new IndexOutOfBoundsException("row index i is out of bounds");
	    } else {
    		i = i -1;
	    	j = j - 1;
	    	int firstID = xyToID(i,j);
	    	int aboveID = xyToID(i -1,j);
	    	int belowID = xyToID(i + 1,j);
	    	int leftID = xyToID(i, j-1);
	    	int rightID = xyToID(i, j +1);

	    	//Opens a site if closed ie. 1 --> 2
	    	this.grid[i][j] = true;
	    	
	    	//Connects opened square in the top row to Virtual Top
	    	if (i == 0) {
	    		perc.union(firstID, virtualTop);
	    	}
	    	
	    	//Connects opened square on bottom row to Virtual Bottom
	    	if (i == this.size - 1) {
	    		perc.union(firstID, virtualBottom);
	    	}
	    		
	    	//If site above is open, connects the two sites
	    	if (i > 0) {
		    	if (this.grid[i-1][j] == true) {
		    		this.perc.union(firstID,aboveID);
		    	}
	    	}
	    	
	    	//If site below is open, connects the two sites
	    	if (i != this.size - 1) {
		    	if (this.grid[i+1][j] == true) {
		    		this.perc.union(firstID, belowID);
		    	}
	    	}
	    	
	    	//If site to the left is open, connects the two sites
	    	if (j != 0) {
		    	if (this.grid[i][j-1] == true) {
		    		this.perc.union(firstID, leftID);
		    	}
	    	}
	    	
	    	//If site to the right is open, connects the two sites
	    	if (j != this.size - 1) {
		    	if (this.grid[i][j+1] == true) {
		    		this.perc.union(firstID, rightID);
		    	}
	    	}
	    }
    }
    
    public boolean isOpen(int i, int j) {
    	if ((i < 1 || i > this.size) || (j < 1 || j > this.size)) {
	    	throw new IndexOutOfBoundsException("row index i is out of bounds");
	    } else {
	    	return this.grid[i-1][j-1];
	    }	
    }
    
    public boolean isFull(int i, int j) {
    	if ((i < 1 || i > this.size) || (j < 1 || j > this.size)) {
	    	throw new IndexOutOfBoundsException("row index i is out of bounds");
	    } else {	
	    	i -= 1;
	    	j -= 1;
	    	return (perc.connected(xyToID(i,j), virtualTop));
	    }	
    }
    
    public boolean percolates() {
    	return perc.connected(virtualTop,virtualBottom);
    }
    
    private int xyToID(int x, int y) {
    	int key = (x)*this.size + y + 1;
    	return key;
    }
    
    
	public static void main(String[] args) {
		
	}
}
