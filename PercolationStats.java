import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Arrays;

public class PercolationStats {
	
	private int tCount;
	private double[] array;
	private double numberOfTimes;
	private double size;
	private double openSites;

	public PercolationStats(int N, int T) {
		if (N <= 0 || T <=0) {
			throw new java.lang.IllegalArgumentException();
		} else {
			this.size = N;
			this.numberOfTimes = T;
			this.tCount = 0;
			this.array = new double[T];
			while(this.tCount < T) {
				Percolation test = new Percolation(N);
				this.openSites = 0;
				while (test.percolates() == false) {
					int i = StdRandom.uniform(1, N + 1);
					int j = StdRandom.uniform(1, N + 1);
					if (test.isOpen(i, j) == false) {
						test.open(i,j);
						this.openSites += 1;
					}
				}
				this.array[tCount] = (this.openSites/(N*N));
				this.tCount += 1;
			}	
		}	
		
	}
	
	public double mean() {
		return StdStats.mean(this.array);
	}
	
	public double stddev() {
		return StdStats.stddev(this.array);
	}
	
	public double confidenceLo() {
		double low = mean() - ((1.96*stddev())/(Math.sqrt(this.numberOfTimes)));
		return low;
	}
	
	public double confidenceHi() {
		double hi = mean() + ((1.96*stddev())/(Math.sqrt(this.numberOfTimes)));
		return hi;
	}
	
	public static void main(String[] args) {
		
		int firstArg = Integer.parseInt(args[0]);
		int secondArg = Integer.parseInt(args[1]);
		PercolationStats test = new PercolationStats(firstArg, secondArg);
		System.out.println("mean                    = " + test.mean());
		System.out.println("stddev                  = " + test.stddev());
		System.out.println("95% Confidence Interval = " + test.confidenceLo() + ", " + test.confidenceHi());
		
	}
}
