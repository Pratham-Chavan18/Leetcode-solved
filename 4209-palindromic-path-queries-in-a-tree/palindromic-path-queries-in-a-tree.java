import java.util.*;

class Solution {
    int N;
    int[] bit;
    int LOG;
    int[][] up;
    int[] depth;

    void add(int i, int x) {
        for (; i <= N + 1; i += i & -i) bit[i] ^= x;
    }

    int get(int i) {
        int r = 0;
        for (; i > 0; i -= i & -i) r ^= bit[i];
        return r;
    }

    void rangeXor(int l, int r, int x) {
        add(l, x);
        add(r + 1, x);
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) { int t = u; u = v; v = t; }
        int d = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++) if (((d >> j) & 1) == 1) u = up[j][u];
        if (u == v) return u;
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) { u = up[j][u]; v = up[j][v]; }
        }
        return up[0][u];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        N = n;
        bit = new int[n + 2];
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] nxt = new int[2 * n];
        int[] to = new int[2 * n];
        int cnt = 0;
        for (int[] e : edges) {
            to[cnt] = e[1]; nxt[cnt] = head[e[0]]; head[e[0]] = cnt++;
            to[cnt] = e[0]; nxt[cnt] = head[e[1]]; head[e[1]] = cnt++;
        }
        LOG = 1;
        while ((1 << LOG) < n) LOG++;
        up = new int[LOG][n];
        depth = new int[n];
        int[] tin = new int[n], tout = new int[n];
        int[] it = head.clone();
        int[] stack = new int[n];
        int sp = 0, timer = 1;
        stack[sp++] = 0;
        tin[0] = 1;
        while (sp > 0) {
            int u = stack[sp - 1];
            if (it[u] != -1) {
                int e = it[u];
                it[u] = nxt[e];
                int v = to[e];
                if (v == up[0][u]) continue;
                up[0][v] = u;
                depth[v] = depth[u] + 1;
                tin[v] = ++timer;
                stack[sp++] = v;
            } else {
                tout[u] = timer;
                sp--;
            }
        }
        for (int j = 1; j < LOG; j++)
            for (int v = 0; v < n; v++) up[j][v] = up[j - 1][up[j - 1][v]];

        char[] cs = s.toCharArray();
        for (int u = 0; u < n; u++) rangeXor(tin[u], tout[u], 1 << (cs[u] - 'a'));

        List<Boolean> res = new ArrayList<>();
        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);
                if (cs[u] != c) {
                    int x = (1 << (cs[u] - 'a')) ^ (1 << (c - 'a'));
                    rangeXor(tin[u], tout[u], x);
                    cs[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v);
                int m = get(tin[u]) ^ get(tin[v]) ^ (1 << (cs[l] - 'a'));
                res.add((m & (m - 1)) == 0);
            }
        }
        return res;
    }
}