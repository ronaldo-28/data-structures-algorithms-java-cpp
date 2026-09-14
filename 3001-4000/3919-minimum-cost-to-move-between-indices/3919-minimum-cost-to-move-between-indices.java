class Solution {
    public int[] minCost(int[] nums, int[][] queries) {
        int n = nums.length;

        int[] closest = new int[n];

        // compute closest
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                closest[i] = 1;
            } else if (i == n - 1) {
                closest[i] = n - 2;
            } else {
                int left = nums[i] - nums[i - 1];
                int right = nums[i + 1] - nums[i];

                if (left <= right) closest[i] = i - 1;
                else closest[i] = i + 1;
            }
        }

        // forward cost
        long[] forward = new long[n];
        for (int i = 0; i < n - 1; i++) {
            if (closest[i] == i + 1) {
                forward[i + 1] = forward[i] + 1;
            } else {
                forward[i + 1] = forward[i] + (nums[i + 1] - nums[i]);
            }
        }

        // backward cost
        long[] backward = new long[n];
        for (int i = n - 1; i > 0; i--) {
            if (closest[i] == i - 1) {
                backward[i - 1] = backward[i] + 1;
            } else {
                backward[i - 1] = backward[i] + (nums[i] - nums[i - 1]);
            }
        }

        // answer queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];

            if (l < r) {
                ans[i] = (int)(forward[r] - forward[l]);
            } else {
                ans[i] = (int)(backward[r] - backward[l]);
            }
        }

        return ans;
    }
}