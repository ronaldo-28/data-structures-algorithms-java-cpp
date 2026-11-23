class Solution {
    public long minCost(int[] nums) {
        int n = nums.length;
        long res = (long) nums[0] + (long) nums[n - 1];
        if (n == 2) {
            return res;
        }

        long[] ar = new long[n - 2];
        for (int i = 1; i <= n - 2; i++) {
            ar[i - 1] = nums[i];
        }
        Arrays.sort(ar);

        long ans = res;
        for (int i = 0; i < n - 3; i += 2) {
            res += ar[i] + ar[i + 1];
            if (res < ans) {
                ans = res;
            }
        }
        return ans;
    }
}