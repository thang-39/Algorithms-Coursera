import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int gridSize;
    private boolean[] grid;
    private WeightedQuickUnionUF wquGrid;
    private WeightedQuickUnionUF wquFull;
    private int virtualBottom;
    private int virtualTop;
    private int openSiteCount;




    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.gridSize = n;
        this.grid = new boolean[n*n];
        this.wquGrid = new WeightedQuickUnionUF(n*n + 2); // includes virtual top, bottom
        this.wquFull = new WeightedQuickUnionUF(n*n + 1); // includes virtual top
        this.virtualBottom = n*n + 1;
        this.virtualTop = n*n;
        this.openSiteCount = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > gridSize || col > gridSize)
            throw new IllegalArgumentException();

        if (isOpen(row,col))
            return;

        grid[(row-1) * gridSize + col - 1] = true;
        openSiteCount++;

        if (row == 1) {
            wquGrid.union(col-1, virtualTop);
            wquFull.union(col-1, virtualTop);
        }

        if (row == gridSize)
            wquGrid.union((row-1)* gridSize +col-1, virtualBottom);

        // above
        if (row > 1 && isOpen(row-1, col)) {
            wquGrid.union((row - 1 - 1) * gridSize + col - 1, (row - 1) * gridSize + col - 1);
            wquFull.union((row - 1 - 1) * gridSize + col - 1, (row - 1) * gridSize + col - 1);
        }
        // left
        if (col > 1 && isOpen(row, col-1)) {
            wquGrid.union((row - 1) * gridSize + (col - 1) - 1, (row - 1) * gridSize + col - 1);
            wquFull.union((row - 1) * gridSize + (col - 1) - 1, (row - 1) * gridSize + col - 1);
        }
        // right
        if (col < gridSize && isOpen(row, col+1)) {
            wquGrid.union((row - 1) * gridSize + (col + 1) - 1, (row - 1) * gridSize + col - 1);
            wquFull.union((row - 1) * gridSize + (col + 1) - 1, (row - 1) * gridSize + col - 1);
        }
        // under
        if (row < gridSize && isOpen(row+1, col)) {
            wquGrid.union((row + 1 - 1) * gridSize + col - 1, (row - 1) * gridSize + col - 1);
            wquFull.union((row + 1 - 1) * gridSize + col - 1, (row - 1) * gridSize + col - 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row,int col) {
        if (row < 1 || col < 1 || row > gridSize || col > gridSize)
            throw new IllegalArgumentException();
        return grid[(row-1)* gridSize + col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row,int col) {
        if (row < 1 || col < 1 || row > gridSize || col > gridSize)
            throw new IllegalArgumentException();
        return wquFull.find((row-1)* gridSize + col - 1) == wquFull.find(virtualTop);

//        if (row == 1) {
//            return isOpen(row, col);
//        }
//        if (openSiteCount != 0)
//            return wquGrid.find((row-1)* gridSize + col - 1) == wquGrid.find(0);
//        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquGrid.find(virtualTop) == wquGrid.find(virtualBottom);
//        return wquGrid.find(0) == wquGrid.find(gridSize * gridSize -1);
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
