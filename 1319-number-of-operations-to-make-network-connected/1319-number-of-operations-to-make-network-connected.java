class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int components = n;

        for (int[] connection : connections) {
            int root1 = find(parent, connection[0]);
            int root2 = find(parent, connection[1]);

            if (root1 != root2) {
                parent[root1] = root2;
                components--;
            }
        }

        return components - 1;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }

        return parent[x];
    }
}