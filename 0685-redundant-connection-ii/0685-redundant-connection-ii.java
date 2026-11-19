class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];

        int[] candidate1 = null;
        int[] candidate2 = null;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (parent[v] == 0) {
                parent[v] = u;
            } else {
                candidate1 = new int[]{parent[v], v};
                candidate2 = new int[]{u, v};
                edge[1] = 0;
            }
        }

        DSU dsu = new DSU(n);

        for (int[] edge : edges) {
            if (edge[1] == 0) {
                continue;
            }

            int u = edge[0];
            int v = edge[1];

            if (!dsu.union(u, v)) {
                if (candidate1 == null) {
                    return edge;
                }
                return candidate1;
            }
        }

        return candidate2;
    }

    class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;
            }

            parent[rootY] = rootX;
            return true;
        }
    }
}