class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
        }

        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(k);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adj[u]) {
                if (!suspicious[v]) {
                    suspicious[v] = true;
                    queue.offer(v);
                }
            }
        }

        for (int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];

            if (!suspicious[u] && suspicious[v]) {
                List<Integer> allMethods = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }

                return allMethods;
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }
}