public class Percolation {

    private boolean[][] map;

    private Integer openCount;

    private int id[];

    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if(n <= 0) throw new IllegalArgumentException();

        map = new boolean[n][n];
        this.n = n;
        openCount = 0;
        id = new int[n*n+2];

        for(int i=0;i<id.length;i++){
            id[i] = i;
        }

        //start virtual point
        for(int i=0;i<n;i++){
            union(i, n*n);
        }

        // end virtual point;
        for(int i=0;i<n;i++){
            union(n*(n-1)+i, n*n+1);
        }
    }

    private void union(int firstIdx, int secondIdx){
        int needToChangeIdx = id[firstIdx];
        for(int i=0; i<id.length;i++){
            if(id[i] == needToChangeIdx){
                id[i] = id[secondIdx];
            }
        }
    }

    private boolean connected(int firstIdx, int secondIdx){
//        int firstIdxParent = firstIdx;
//        int secondIdxParent = secondIdx;
//        while (firstIdxParent != id[firstIdxParent]){
//            firstIdxParent = id[firstIdxParent];
//        }
//        while (secondIdxParent != id[secondIdxParent]){
//            secondIdxParent = id[secondIdxParent];
//        }
//        return firstIdxParent == secondIdxParent;
        return id[firstIdx] == id[secondIdx];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(row<=0||row>n||col<=0||col>n) throw new IllegalArgumentException();

        int rowIdx = row-1;
        int colIdx = col-1;
        if(!map[rowIdx][colIdx]) {
            map[rowIdx][colIdx] = true;
            openCount++;

            int leftPointCol = colIdx - 1;
            int rightPointCol = colIdx + 1;
            int upPointRow = rowIdx - 1;
            int downPointRow = rowIdx + 1;

            if(leftPointCol>=0 && isOpen(rowIdx+1, leftPointCol+1)) union(getPointIdx(rowIdx, colIdx), getPointIdx(rowIdx, leftPointCol));
            if(rightPointCol<n && isOpen(rowIdx+1, rightPointCol+1)) union(getPointIdx(rowIdx, colIdx), getPointIdx(rowIdx, rightPointCol));
            if(upPointRow>=0 && isOpen(upPointRow+1, colIdx+1)) union(getPointIdx(rowIdx, colIdx), getPointIdx(upPointRow, colIdx));
            if(downPointRow<n && isOpen(downPointRow+1, colIdx+1)) union(getPointIdx(rowIdx, colIdx), getPointIdx(downPointRow, colIdx));
        }

    }

    private int getPointIdx(int row, int col){
        return row * n + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row<=0||row>n||col<=0||col>n) throw new IllegalArgumentException();
        int rowIdx = row-1;
        int colIdx = col-1;
        return map[rowIdx][colIdx];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(row<=0||row>n||col<=0||col>n) throw new IllegalArgumentException();
        int rowIdx = row-1;
        int colIdx = col-1;
        if(!isOpen(row, col)) return false;
        return connected(n*n, getPointIdx(rowIdx, colIdx));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openCount;
    }

    // does the system percolate?
    public boolean percolates(){
        if(n == 1 && numberOfOpenSites() == 0) return false;
        return connected(n*n, n*n+1);
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation percolation = new Percolation(3);
        percolation.open(1,1);
        System.out.println(percolation.percolates());
        percolation.open(2,2);
        System.out.println(percolation.percolates());
        percolation.open(1,2);
        System.out.println(percolation.percolates());
        percolation.open(3,2);
        System.out.println(percolation.percolates());
    }
}