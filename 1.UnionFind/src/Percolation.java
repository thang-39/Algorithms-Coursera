import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private boolean[] grid;
    private QuickFindUF open;
    private int openSiteCount;



    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n<0");
        this.n = n;
        this.grid = new boolean[n*n];
        this.open = new QuickFindUF(n*n);
        this.openSiteCount = 0;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IllegalArgumentException("out of bound");
        grid[row*(n-1)+col] = true;
        openSiteCount++;

        if (row == 1) {
            open.union(col,0);
        }

        //above
        if (isOpen(row-1,col))
            open.union(row*(n-1)+col, (row-1)*(n-1) + col);
        // left
        if (col > 1 && isOpen(row, col-1))
            open.union(row*(n-1)+col, row*(n-1)+(col-1));
        // right
        if (col < n && isOpen(row, col+1))
            open.union(row*(n-1)+col, row*(n-1)+(col+1));
        // under
        if (row < n && isOpen(row+1,col))
            open.union(row*(n-1)+col, (row+1)*(n-1)*col);

        if (row == n) {
            open.union(row*(n-1)+col, (row-1)*(n-1) + col); // above
            open.union(row*(n-1)+col, row*(n-1)+(col-1)); // left
            open.union(row*(n-1)+col, row*(n-1)+(col+1)); // right
        }
        if (col == 1) {
            open.union(row*(n-1)+col, (row-1)*(n-1) + col); // above
            open.union(row*(n-1)+col, row*(n-1)+(col+1)); // right
            open.union(row*(n-1)+col, (row+1)*(n-1)*col); // under
        }
        if (col == n) {
            open.union(row*(n-1)+col, (row-1)*(n-1) + col); //above
            open.union(row*(n-1)+col, row*(n-1)+(col-1)); // left
            open.union(row*(n-1)+col, (row+1)*(n-1)*col); // under
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row*(n-1)+col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return open.connected(row*(n-1)+col,0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = n*n; i > n*n-n ; i--) {
            if (open.connected(i,0))
                return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(2,1);
        p.open(3,2);
        p.open(1,1);
        p.open(4,2);
        p.percolates();
        p.numberOfOpenSites();
        p.isFull(3,2);
    }
}
