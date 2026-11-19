class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        DSU alice = new DSU(n);
        DSU bob = new DSU(n);

        int usedEdges = 0;

        // Process type 3 edges first (shared by Alice and Bob)
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                boolean a = alice.union(edge[1], edge[2]);
                boolean b = bob.union(edge[1], edge[2]);

                if (a || b) {
                    usedEdges++;
                }
            }
        }

        // Process Alice-only edges
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (alice.union(edge[1], edge[2])) {
                    usedEdges++;
                }
            }
        }

        // Process Bob-only edges
        for (int[] edge : edges) {
            if (edge[0] == 2) {
                if (bob.union(edge[1], edge[2])) {
                    usedEdges++;
                }
            }
        }

        if (alice.components != 1 || bob.components != 1) {
            return -1;
        }

        return edges.length - usedEdges;
    }

    class DSU {
        int[] parent;
        int[] rank;
        int components;

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            components = n;

            for (int i = 1; i <= n; i++) {
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

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            components--;
            return true;
        }
    }
}