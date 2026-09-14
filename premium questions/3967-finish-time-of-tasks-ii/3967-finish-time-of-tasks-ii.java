class Solution {
    final long inf = (long)1E18;
    List<Integer>[] adj;
    TreeInfo[] subRes;
    long[] downTime;
    long[] ans;
    int[] base;
    long res;
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        base = baseTime;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        subRes = new TreeInfo[n];
        downTime = new long[n];
        ans = new long[n];

        dfs(0, -1);
        
        res = inf;
        reroot(0, -1, -1);
        return res;
    }

    private void dfs(int u, int fa) {
        subRes[u] = new TreeInfo();
        int childCnt = 0;
        for (int v : adj[u]) {
            if (v == fa) continue;
            dfs(v, u);
            subRes[u].update(downTime[v], v);
            childCnt++;
        }
        downTime[u] = childCnt == 0 ? base[u] : (2 * subRes[u].mx1 - subRes[u].mn1 + base[u]);
    }

    private void reroot(int u, int fa, long fromUp) {
        TreeInfo cur = subRes[u];
        if (fromUp != -1) cur.update(fromUp, fa);

        int neighborCnt = adj[u].size();
        ans[u] = neighborCnt == 0 ? base[u] : (2 * cur.mx1 - cur.mn1 + base[u]);
        res = Math.min(res, ans[u]);

        for (int v : adj[u]) {
            if (v == fa) continue;
            long useMx = v == cur.maxChild ? cur.mx2 : cur.mx1;
            long useMn = v == cur.minChild ? cur.mn2 : cur.mn1;

            long up;
            if (neighborCnt == 1) up = base[u];
            else up = 2 * useMx - useMn + base[u];
            reroot(v, u, up);
        }
    }

    class TreeInfo {
        long mx1 = -inf, mx2 = -inf;
        long mn1 = inf, mn2 = inf;
        int maxChild = -1, minChild = -1;
        private void update(long val, int childId) {
            if (val > mx1) {
                mx2 = mx1;
                mx1 = val;
                maxChild = childId;
            } else if (val > mx2) mx2 = val;

            if (val < mn1) {
                mn2 = mn1;
                mn1 = val;
                minChild = childId;
            } else if (val < mn2) mn2 = val;
        }
    }
}