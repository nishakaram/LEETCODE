class Solution {
    public int minDays(int[][] grid) {
        if (countIslands(grid) != 1) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;

                    if (countIslands(grid) != 1) {
                        grid[i][j] = 1;
                        return 1;
                    }

                    grid[i][j] = 1;
                }
            }
        }

        return 2;
    }

    private int countIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    count++;
                    if (count > 1) {
                        return count;
                    }
                    dfs(grid, visited, i, j);
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, boolean[][] visited, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;

        if (row < 0 || row >= m || col < 0 || col >= n ||
            grid[row][col] == 0 || visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        dfs(grid, visited, row + 1, col);
        dfs(grid, visited, row - 1, col);
        dfs(grid, visited, row, col + 1);
        dfs(grid, visited, row, col - 1);
    }
}