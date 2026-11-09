class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<long[]> heap = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int[] server = new int[k];
        TreeSet<Integer> free = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            free.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {
            long arr = arrival[i];
            int l = load[i];

            while (!heap.isEmpty() && heap.peek()[0] <= arr) {
                long[] node = heap.poll();
                free.add((int) node[1]);
            }

            if (free.isEmpty()) {
                continue;
            }

            Integer s = free.ceiling(i % k);
            if (s == null) {
                s = free.first();
            }

            free.remove(s);

            heap.offer(new long[]{arr + l, s});
            server[s]++;
        }

        int max_ = 0;
        for (int count : server) {
            max_ = Math.max(max_, count);
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (server[i] == max_) {
                res.add(i);
            }
        }

        return res;
    }
}