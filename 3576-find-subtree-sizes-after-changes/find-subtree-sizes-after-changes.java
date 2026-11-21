class Solution {
    private List<Integer>[] adj;
    private int[] newParent;
    private int[] charAncestor;
    private int[] subtreeSize;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        newParent = parent.clone();
        charAncestor = new int[26];
        Arrays.fill(charAncestor, -1);

        dfs1(0);

        for (int i = 0; i < n; i++) {
            adj[i].clear();
        }
        for (int i = 1; i < n; i++) {
            adj[newParent[i]].add(i);
        }

        subtreeSize = new int[n];
        dfs2(0);
        return subtreeSize;
    }

    private void dfs1(int u) {
        int c = s.charAt(u) - 'a';
        int prev = charAncestor[c];
        if (prev != -1) {
            newParent[u] = prev;
        }
        charAncestor[c] = u;

        for (int v : adj[u]) {
            dfs1(v);
        }

        charAncestor[c] = prev;
    }

    private int dfs2(int u) {
        int sz = 1;
        for (int v : adj[u]) {
            sz += dfs2(v);
        }
        return subtreeSize[u] = sz;
    }
}