import java.util.Arrays;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (n == 1) return 0;
        int m = edges.length;
        int[] head = new int[n], nxt = new int[m], to = new int[m], w = new int[m];
        Arrays.fill(head, -1);
        for (int i = 0; i < m; i++) {
            to[i] = edges[i][1];
            w[i] = edges[i][2];
            nxt[i] = head[edges[i][0]];
            head[edges[i][0]] = i;
        }
        char[] lab = labels.toCharArray();

        int total = n * k;
        int[] dist = new int[total];
        Arrays.fill(dist, Integer.MAX_VALUE);
        long[] heap = new long[1024];
        int size = 0;

        dist[0] = 0;
        heap[size++] = 0L << 22 | 0;

        while (size > 0) {
            long top = heap[0];
            heap[0] = heap[--size];
            siftDown(heap, size, 0);
            int d = (int) (top >>> 22);
            int state = (int) (top & ((1 << 22) - 1));
            if (d > dist[state]) continue;
            int u = state / k, c = state % k;
            if (u == n - 1) return d;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                int nc = lab[v] == lab[u] ? c + 1 : 0;
                if (nc >= k) continue;
                int ns = v * k + nc;
                int nd = d + w[e];
                if (nd < dist[ns]) {
                    dist[ns] = nd;
                    if (size == heap.length) heap = Arrays.copyOf(heap, size * 2);
                    heap[size] = ((long) nd << 22) | ns;
                    siftUp(heap, size++);
                }
            }
        }
        return -1;
    }

    static void siftUp(long[] h, int i) {
        while (i > 0) {
            int p = (i - 1) >>> 1;
            if (h[p] <= h[i]) break;
            long t = h[p]; h[p] = h[i]; h[i] = t;
            i = p;
        }
    }

    static void siftDown(long[] h, int size, int i) {
        while (true) {
            int l = 2 * i + 1, r = l + 1, s = i;
            if (l < size && h[l] < h[s]) s = l;
            if (r < size && h[r] < h[s]) s = r;
            if (s == i) break;
            long t = h[s]; h[s] = h[i]; h[i] = t;
            i = s;
        }
    }
}