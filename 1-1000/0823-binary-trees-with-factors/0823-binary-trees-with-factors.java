class Solution {
    private static final int MOD = 1_000_000_007;

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;

        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(arr[i], i);
        }

        long[] dp = new long[n];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1; // single-node tree

            for (int j = 0; j < i && (long) arr[j] * arr[j] <= arr[i]; j++) {
                if (arr[i] % arr[j] != 0) continue;   // ✅ FIX

                int b = arr[i] / arr[j];
                if (!indexMap.containsKey(b)) continue;

                int k = indexMap.get(b);
                if (k > i) continue;   // ensure b <= root index
                //if (j > k) continue;   // prevent double counting

                if (j == k) {
                    dp[i] = (dp[i] + dp[j] * dp[k]) % MOD;
                } else {
                    dp[i] = (dp[i] + 2 * dp[j] * dp[k]) % MOD;
                }
            }

            ans = (ans + dp[i]) % MOD;
        }

        return (int) ans;
    }
}