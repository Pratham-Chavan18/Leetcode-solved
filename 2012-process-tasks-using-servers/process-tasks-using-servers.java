class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length, m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<int[]> heapsmol = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            }
        );

        PriorityQueue<long[]> heaplarge = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Long.compare(a[0], b[0]);
                if (a[1] != b[1])
                    return Long.compare(a[1], b[1]);
                return Long.compare(a[2], b[2]);
            }
        );

        for (int i = 0; i < n; i++) {
            heapsmol.offer(new int[]{servers[i], i});
        }

        for (int i = 0; i < m; i++) {
            while (!heaplarge.isEmpty() && heaplarge.peek()[0] <= i) {
                long[] node = heaplarge.poll();
                heapsmol.offer(new int[]{(int)node[1], (int)node[2]});
            }

            if (!heapsmol.isEmpty()) {
                int[] node = heapsmol.poll();
                int weight = node[0];
                int index = node[1];

                ans[i] = index;
                heaplarge.offer(new long[]{
                    (long)i + tasks[i], weight, index
                });
            } else {
                long[] node = heaplarge.poll();
                long time = node[0];
                long weight = node[1];
                int index = (int)node[2];

                ans[i] = index;
                heaplarge.offer(new long[]{
                    time + tasks[i], weight, index
                });
            }
        }

        return ans;
    }
}