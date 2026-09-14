class Solution {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size();
        int n = cost.get(0).size();
        
        // minCost[j] = minimum cost to connect any node in group 1 to node j in group 2
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            List<Integer> c = cost.get(i);
            for (int j = 0; j < n; j++) {
                minCost[j] = Math.min(minCost[j], c.get(j));
            }
        }

        // dp[i][mask] = min cost to connect nodes [i ... m - 1] in group 1
        // where mask represents which nodes in group 2 have already been connected
        int[][] dp = new int[m + 1][1 << n];
        for (int[] row : dp) Arrays.fill(row, -1);

        // DFS from node 0 in group 1, with no nodes in group 2 connected
        return dfs(cost, minCost, dp, 0, 0, m, n);
    }

    private int dfs(List<List<Integer>> cost, int[] minCost, int[][] dp, int i, int mask, int m, int n) {
        if (dp[i][mask] != -1) return dp[i][mask];
        
        if (i == m) {
            // all nodes in group 1 have been processed
            // if a node in group 2 is not connected yet, add its minimum possible cost
            int res = 0;
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) == 0) {
                    res += minCost[j];
                }
            }

            return dp[i][mask] = res;
        }


        int res = Integer.MAX_VALUE;
        List<Integer> c = cost.get(i);

        // try connecting the current node i in group 1 to every node j in group 2
        for (int j = 0; j < n; j++) {
            // mark the j node in group 2 as connected
            res = Math.min(res, c.get(j) + dfs(cost, minCost, dp, i + 1, mask | (1 << j), m, n));
        }

        return dp[i][mask] = res;
    }
}