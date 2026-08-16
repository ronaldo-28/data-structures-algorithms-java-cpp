class Solution {
    public int maximumPoints(int[][] edges, int[] coins, int k) {
        final int n = edges.length + 1;

        int[] mixs = new int[n];
        int[] degrees = new int[n];
        degrees[0] = 2;

        for (int[] edge : edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;
            mixs[edge[0]] ^= edge[1];
            mixs[edge[1]] ^= edge[0];
        }

        int[] queue = new int[n];
        int left = 0, right = 0;

        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                queue[right++] = i;
            }
        }

        int maxCoin = 0, coinBitlength = 0;
        for (int coin : coins) {
            maxCoin = Math.max(maxCoin, coin);
        }
        for (; maxCoin > 0; maxCoin >>= 1) {
            coinBitlength++;
        }
        coinBitlength = Math.max(coinBitlength, 2);

        int[][] dps = new int[n][coinBitlength + 1];
        for (; left < right; left++) {
            int node = queue[left], parent = mixs[node];
            degrees[parent]--;
            mixs[parent] ^= node;
            if (degrees[parent] == 1) {
                queue[right++] = parent;
            }
            int[] nodeRes = dps[node], parentRes = dps[parent];
            for (int i = 0, coin = coins[node]; i < coinBitlength; i++, coin >>= 1) {
                int theK = coin - k + nodeRes[i];
                int theHalf = (coin >> 1) + nodeRes[i + 1];
                nodeRes[i] = Math.max(theHalf, theK);
                parentRes[i] += nodeRes[i];
            }
        }

        return Math.max(coins[0] - k + dps[0][0], (coins[0] >> 1) + dps[0][1]);
    }
}