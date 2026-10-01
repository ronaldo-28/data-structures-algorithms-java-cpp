class Solution {
    public int minimumValueSum(int[] nums, int[] andValues) {
        int n = nums.length;
        int m = andValues.length;

        final int INF = Integer.MAX_VALUE / 2;

        // prev[i] = minimum cost to divide first i elements
        // into (j-1) groups
        int[] prev = new int[n + 1];
        Arrays.fill(prev, INF);
        prev[0] = 0;

        for (int j = 1; j <= m; j++) {

            int[] cur = new int[n + 1];
            Arrays.fill(cur, INF);

            /*
             * Each state:
             * andVal = AND of a subarray ending at current index
             * cost   = minimum cost before that subarray started
             */
            int[] oldAnd = new int[32];
            int[] oldCost = new int[32];
            int oldSize = 0;

            for (int i = 0; i < n; i++) {

                int[] newAnd = new int[32];
                int[] newCost = new int[32];
                int newSize = 0;

                // Start a new subarray at i
                if (prev[i] != INF) {
                    newAnd[newSize] = nums[i];
                    newCost[newSize] = prev[i];
                    newSize++;
                }

                // Extend all previous subarrays
                for (int k = 0; k < oldSize; k++) {

                    int value = oldAnd[k] & nums[i];
                    int cost = oldCost[k];

                    // Merge equal consecutive AND values
                    if (newSize > 0 &&
                        newAnd[newSize - 1] == value) {

                        newCost[newSize - 1] =
                            Math.min(newCost[newSize - 1], cost);

                    } else {
                        newAnd[newSize] = value;
                        newCost[newSize] = cost;
                        newSize++;
                    }
                }

                // If this subarray's AND matches andValues[j - 1]
                for (int k = 0; k < newSize; k++) {

                    if (newAnd[k] == andValues[j - 1]) {
                        cur[i + 1] = Math.min(
                            cur[i + 1],
                            newCost[k] + nums[i]
                        );
                    }
                }

                // Move current states to old states
                System.arraycopy(newAnd, 0, oldAnd, 0, newSize);
                System.arraycopy(newCost, 0, oldCost, 0, newSize);
                oldSize = newSize;
            }

            prev = cur;
        }

        return prev[n] == INF ? -1 : prev[n];
    }
}