class Solution {
    private int timer = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);

            graph[u].add(v);
            graph[v].add(u);
        }

        int[] discovery = new int[n];
        int[] low = new int[n];

        List<List<Integer>> result = new ArrayList<>();

        dfs(0, -1, graph, discovery, low, result);

        return result;
    }

    private void dfs(int node, int parent,
                     List<Integer>[] graph,
                     int[] discovery, int[] low,
                     List<List<Integer>> result) {

        discovery[node] = low[node] = ++timer;

        for (int neighbor : graph[node]) {
            if (neighbor == parent) {
                continue;
            }

            if (discovery[neighbor] == 0) {
                dfs(neighbor, node, graph, discovery, low, result);

                low[node] = Math.min(low[node], low[neighbor]);

                if (low[neighbor] > discovery[node]) {
                    result.add(Arrays.asList(node, neighbor));
                }
            } else {
                low[node] = Math.min(low[node], discovery[neighbor]);
            }
        }
    }
}