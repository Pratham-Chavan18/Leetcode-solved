class Solution {
    static Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[0][0] == ')' || grid[rows - 1][cols - 1] == '(') {
            return false;
        }

        if ((rows + cols - 1) % 2 != 0) {
            return false;
        }

        memo = new Boolean[101][101][201];

        return search(grid, 0, 0, 0);
    }

    private boolean search(char[][] grid, int row, int col, int balance) {
        if (grid[row][col] == '(') {
            balance++;
        } else {
            balance--;
        }

        if (balance < 0) {
            return false;
        }

        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return balance == 0;
        }

        if (memo[row][col][balance] != null) {
            return memo[row][col][balance];
        }

        boolean canFormValidPath = false;

        if (row + 1 < grid.length) {
            canFormValidPath = search(grid, row + 1, col, balance);
        }

        if (!canFormValidPath && col + 1 < grid[0].length) {
            canFormValidPath = search(grid, row, col + 1, balance);
        }

        return memo[row][col][balance] = canFormValidPath;
    }
}