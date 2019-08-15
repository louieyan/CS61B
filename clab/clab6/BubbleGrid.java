/**
 * @author Lei Yan
 * @date 2019/08/15
 * @source https://github.com/zangsy/cs61b_sp19/blob/master/clab/clab6/BubbleGrid.java
 */
public class BubbleGrid {
    private int[][] grid;

    // Four orthogonally adjacent directions
    private int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rowNum; // number of rows
    private int colNum; // number of columns
    private int ceiling; // the ceiling

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        rowNum = grid.length;
        colNum = grid[0].length;
        ceiling = 0;        // Ceiling is zero
        this.grid = grid;   // Every stuck bubble should connect to ceiling
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        UnionFind uf = new UnionFind(rowNum * colNum + 1); // 0 is the ceiling, so need one more space.

        /* For every dart in darts, if the dart hit a bubble, we change
         * the value of grid at that indices to show that bubble was popped. */
        for (int[] dart : darts) {
            int raw = dart[0];
            int col = dart[1];
            if (grid[raw][col] == 1) { // if hit a bubble
                grid[raw][col] = 2;   // change from 1 to 2
            }
        }

        /* Next, we use the rest of bubble to construct sets.
         * There are tow situations here:
         * 1. grid[i][j] and it's neighbors are connected to the ceiling.
         * 2. grid[i][j] are it's neighbors are NOT connected to the ceiling */
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == 1) {
                    unionNeighbor(i, j, uf);
                }
            }
        }


        int[] result = new int[darts.length]; // Initialize the result array.
        int count = uf.sizeOf(ceiling);       // Equal to (#stuck bubbles + 1)

        /* Reversely reconstruct the original stuck bubble set. */
        for (int i = darts.length - 1; i >= 0; i--) {
            int row = darts[i][0];
            int col = darts[i][1];
            if (grid[row][col] == 2) { // If there was a bubble
                grid[row][col] = 1;    // change back to 1.
                unionNeighbor(row, col, uf); // Connect with it's neighbors
                /* 1. If this bubble is connected to the ceiling, but it's neighbors not,
                 * the size of stuck bubble set will increase 1. This means after this
                 * dart is shot, no bubble will drop.
                 * 2. If this bubble and it's neighbors are connected to the ceiling:
                 *  2.1. If it's neighbors was connected to the ceiling after this bubble' pop
                 *       (which means it's neighbors can connected to the ceiling through other
                 *        bubbles), no bubble will drop.
                 *  2.2. If it's neighbors wasn't connected to the ceiling before (which means
                 *       this bubble is the only way to connect to the ceiling), some bubbles will drop. */
            }

            int newCount = uf.sizeOf(ceiling); // New (#stuck bubbles + 1)
            if (newCount > count) {            // If increases
                result[i] = newCount - count - 1; // Because the dart hit the bubble, so need to minus 1/
            } else {
                result[i] = 0;  // If not increase, no bubble drops.
            }
            count = newCount;   // Reassign to begin next for loop.
        }

        return result;
    }

    /* Union the bubble with it's orthogonally adjacent neighbors */
    private void unionNeighbor(int row, int col, UnionFind uf) {
        if (row == 0) { // The topmost bubbles are stuck
            uf.union(twoDto1D(row, col), ceiling); // so connect it to ceiling.
        }
        for (int[] dir : direction) { // For it's orthogonally adjacent neighbors
            int adjRow = row + dir[0];
            int adjCol = col + dir[1];
            if (adjRow < 0 || adjRow > rowNum - 1 || adjCol < 0 || adjCol > colNum - 1 || grid[adjRow][adjCol] != 1) {
                continue; // adjRow and adjCol should not out of bound, and the adjacent neighbor should be a bubble.
            }
            uf.union(twoDto1D(row, col), twoDto1D(adjRow, adjCol));
        }
    }

    /* Return 1D representation of (x, y) */
    private int twoDto1D(int x, int y) {
        return x * colNum + y + 1;
    }

}
