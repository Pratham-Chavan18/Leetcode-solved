class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, ans = 0;
        int[] dirs = {1, 0, -1, 0, 1};  // down, left, up, right
        int[] island = new int[m * n];  // cells stored as x * n + y, reused for every island
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                long total = grid[i][j];  // can reach 1e11: needs a long
                grid[i][j] = 0;
                int size = 0;
                island[size++] = i * n + j;
                for (int h = 0; h < size; h++) {  // the list grows while we walk it: a BFS queue
                    int x = island[h] / n, y = island[h] % n;
                    for (int d = 0; d < 4; d++) {
                        int a = x + dirs[d], b = y + dirs[d + 1];
                        if (a >= 0 && a < m && b >= 0 && b < n && grid[a][b] != 0) {
                            total += grid[a][b];
                            grid[a][b] = 0;  // sink it so it's counted once
                            island[size++] = a * n + b;
                        }
                    }
                }
                if (total % k == 0) ans++;
            }
        return ans;
    }
}