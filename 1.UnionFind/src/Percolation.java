import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private boolean[] grid;
    private WeightedQuickUnionUF perc;
    private WeightedQuickUnionUF full;
    private int openSiteCount;




    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.n = n;
        this.grid = new boolean[n*n];
        this.perc = new WeightedQuickUnionUF(n*n);
        this.full = new WeightedQuickUnionUF(n*n);
        this.openSiteCount = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IllegalArgumentException();
        if (isOpen(row,col)) {
            return;
        }
        grid[(row-1)*n+col-1] = true;
        openSiteCount++;

        if (row == 1) {
            perc.union(col-1, 0);
        }

        if (row == n) {
            perc.union((row-1)*n+col-1, n*n-1);
        }

        // above
        if (row > 1 && isOpen(row-1, col))
            perc.union((row-1-1)*n + col -1, (row-1)*n + col -1);
        // left
        if (col > 1 && isOpen(row, col-1))
            perc.union((row-1)*n+(col-1) -1, (row-1)*n + col -1);
        // right
        if (col < n && isOpen(row, col+1))
            perc.union((row-1)*n+(col+1) -1, (row-1)*n + col -1);
        // under
        if (row < n && isOpen(row+1, col))
            perc.union((row+1-1)*n + col -1, (row-1)*n + col -1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row,int col) {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IllegalArgumentException();
        return grid[(row-1)*n + col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row,int col) {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IllegalArgumentException();
        if (row == 1) {
            return isOpen(row, col);
        }
        if (openSiteCount != 0)
            return perc.find((row-1)*n + col - 1) == perc.find(0);
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return perc.find(0) == perc.find(n*n-1);
    }

    // test client (optional)
    public static void main(String[] args) {
//        Percolation p = new Percolation(4);
//        p.open(2,1);
//        p.open(3,2);
//        p.open(1,1);
//        p.open(4,2);
////        p.open(2,2);
//        System.out.println(p.perc.find(4));
//        System.out.println(p.perc.find(0));
////        System.out.println(p.perc.connected(0,0));
////        System.out.println(p.perc.connected(4,0));
////
//        System.out.println(p.percolates());
//        System.out.println(p.numberOfOpenSites());
//        System.out.println(p.isFull(3,2));
        Percolation p = new Percolation(10);
        p.open(1,0);
        System.out.println(p.isFull(1, 1));
        System.out.println(p.isOpen(-1, 5));



    }
}
