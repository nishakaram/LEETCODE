class Solution {
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] effort = new int[rows][cols];
        for (int[] row : effort) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        effort[0][0] = 0;
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int r = current[0];
            int c = current[1];
            int currEffort = current[2];

            if (r == rows - 1 && c == cols - 1) {
                return currEffort;
            }

            if (currEffort > effort[r][c]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    int newEffort = Math.max(
                        currEffort,
                        Math.abs(heights[r][c] - heights[nr][nc])
                    );

                    if (newEffort < effort[nr][nc]) {
                        effort[nr][nc] = newEffort;
                        pq.offer(new int[]{nr, nc, newEffort});
                    }
                }
            }
        }

        return 0;
    }
}