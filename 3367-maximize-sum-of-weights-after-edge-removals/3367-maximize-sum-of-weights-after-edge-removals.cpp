class Solution {
public:
    /*
    LOGIC:

    dp[u][0] = maximum answer from subtree of u
               when edge (u,parent) is NOT selected.
               So u can select at most k child edges.

    dp[u][1] = maximum answer from subtree of u
               when edge (u,parent) IS selected.
               So one slot is already used, and u can select
               at most k-1 child edges.

    For every child v:

    1. Don't select edge (u,v):
       contribution = dp[v][0]

    2. Select edge (u,v):
       contribution = w + dp[v][1]

    So the extra benefit of selecting this edge is:

       gain = w + dp[v][1] - dp[v][0]

    First assume that we don't select any child edge:

       base = sum of dp[v][0]

    Then choose the largest positive gains.

    dp[u][0] -> choose at most k gains
    dp[u][1] -> choose at most k-1 gains
    */

    vector<vector<pair<int,int>>> g;
    vector<vector<long long>> dp;

    void dfs(int u, int p, int k) {

        // base = answer when we don't select any edge from u to its children
        long long base = 0;

        // Store the benefit of selecting each child edge
        vector<long long> a;

        for (auto [v, w] : g[u]) {

            // Don't go back to parent
            if (v == p) continue;

            // First solve the child's subtree
            dfs(v, u, k);

            // If we don't take edge (u,v), we get dp[v][0]
            base += dp[v][0];

            // Extra benefit if we take edge (u,v)
            long long gain = 1LL * w + dp[v][1] - dp[v][0];

            a.push_back(gain);
        }

        // Best gains should be considered first
        sort(a.rbegin(), a.rend());

        // Initially don't select any child edge
        dp[u][0] = base;
        dp[u][1] = base;

        for (int i = 0; i < (int)a.size(); i++) {

            // Taking a negative gain can never help
            if (a[i] <= 0) break;

            // Parent edge is NOT selected -> u has k available slots
            if (i < k)
                dp[u][0] += a[i];

            // Parent edge IS selected -> u has only k-1 available slots
            if (i < k - 1)
                dp[u][1] += a[i];
        }
    }

    long long maximizeSumOfWeights(vector<vector<int>>& edges, int k) {
        int n = edges.size() + 1;

        g.resize(n);
        dp.assign(n, vector<long long>(2));

        // Build the tree
        for (auto &e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            g[u].push_back({v, w});
            g[v].push_back({u, w});
        }

        // Root the tree at 0
        dfs(0, -1, k);

        // Root has no parent, so its parent edge is not selected
        return dp[0][0];
    }
};