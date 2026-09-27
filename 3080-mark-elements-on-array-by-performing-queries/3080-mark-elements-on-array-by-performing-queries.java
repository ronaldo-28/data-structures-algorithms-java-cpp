class Solution {
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;

        long totalSum = 0;
        long[] packed = new long[n];
        for (int i = 0; i < n; i++) {
            totalSum += nums[i];
            packed[i] = ((long) nums[i] << 32) | (i & 0xFFFFFFFFL);
        }

        java.util.Arrays.sort(packed);

        boolean[] visited = new boolean[n];
        long[] ans = new long[q];
        int scanPtr = 0;

        for (int i = 0; i < q; i++) {
            int targetIndex = queries[i][0];
            int k = queries[i][1];

            if (!visited[targetIndex]) {
                visited[targetIndex] = true;
                totalSum -= nums[targetIndex];
            }

            while (k > 0 && scanPtr < n) {
                int originalIndex = (int) packed[scanPtr];
                int val = (int) (packed[scanPtr] >> 32);
                scanPtr++;

                if (!visited[originalIndex]) {
                    visited[originalIndex] = true;
                    totalSum -= val;
                    k--;
                }
            }

            ans[i] = totalSum;
        }

        return ans;
    }
}