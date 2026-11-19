class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();

        int startRow = 0;
        int startCol = 0;
        int keyCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = grid[i].charAt(j);

                if (ch == '@') {
                    startRow = i;
                    startCol = j;
                } else if (ch >= 'a' && ch <= 'f') {
                    keyCount++;
                }
            }
        }

        int targetMask = (1 << keyCount) - 1;

        boolean[][][] visited = new boolean[m][n][1 << keyCount];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol, 0, 0});

        visited[startRow][startCol][0] = true;

        int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];
            int keys = current[2];
            int steps = current[3];

            if (keys == targetMask) {
                return steps;
            }

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newRow >= m ||
                    newCol < 0 || newCol >= n) {
                    continue;
                }

                char ch = grid[newRow].charAt(newCol);

                if (ch == '#') {
                    continue;
                }

                int newKeys = keys;

                if (ch >= 'a' && ch <= 'f') {
                    newKeys |= (1 << (ch - 'a'));
                }

                if (ch >= 'A' && ch <= 'F' &&
                    (keys & (1 << (ch - 'A'))) == 0) {
                    continue;
                }

                if (!visited[newRow][newCol][newKeys]) {
                    visited[newRow][newCol][newKeys] = true;
                    queue.offer(new int[]{
                        newRow, newCol, newKeys, steps + 1
                    });
                }
            }
        }

        return -1;
    }
}