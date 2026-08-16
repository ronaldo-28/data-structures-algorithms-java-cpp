class Solution {
    int[] next;
    int[] state;
    int[] ans;

    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();

        next = new int[n];
        state = new int[n];
        ans = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = edges.get(i);
        }

        for (int i = 0; i < n; i++) {
            if (state[i] == 0) {
                dfs(i);
            }
        }

        return ans;
    }

    private void dfs(int node) {
        if (state[node] == 1) {
            // cycle detected → compute cycle size
            int cur = next[node];
            int size = 1;

            while (cur != node) {
                size++;
                cur = next[cur];
            }

            ans[node] = size;

            cur = next[node];
            while (cur != node) {
                ans[cur] = size;
                cur = next[cur];
            }

            return;
        }

        if (state[node] == 2) return;

        state[node] = 1;

        int nxt = next[node];

        dfs(nxt);

        if (ans[node] == 0) {
            ans[node] = ans[nxt] + 1;
        }

        state[node] = 2;
    }
}