import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] percolationPercentList;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n<=0||trials<=0) throw new IllegalArgumentException();

        percolationPercentList = new double[trials];
        for(int t=0;t<trials;t++){
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()){
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;
                percolation.open(row,col);
            }
            percolationPercentList[t] = (double) percolation.numberOfOpenSites() / (n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(percolationPercentList);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(percolationPercentList);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(percolationPercentList.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(percolationPercentList.length);
    }

    // test client (see below)
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, T);
        System.out.println(String.format("mean = %f",percolationStats.mean()));
        System.out.println(String.format("stddev = %f",percolationStats.stddev()));
        System.out.println(String.format("95%s confidence interval = [%f,%f]","%", percolationStats.confidenceLo(), percolationStats.confidenceHi()));
    }

}