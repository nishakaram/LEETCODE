class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int time = current[0];
            int row = current[1];
            int col = current[2];

            if (row == n - 1 && col == n - 1) {
                return time;
            }

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < n &&
                    newCol >= 0 && newCol < n &&
                    !visited[newRow][newCol]) {

                    visited[newRow][newCol] = true;

                    pq.offer(new int[]{
                        Math.max(time, grid[newRow][newCol]),
                        newRow,
                        newCol
                    });
                }
            }
        }

        return -1;
    }
}