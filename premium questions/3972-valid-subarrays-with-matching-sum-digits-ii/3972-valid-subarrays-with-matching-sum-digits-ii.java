class Solution {
    // time = O(nlogS), space = O(n)
    public int countValidSubarrays(int[] nums, int x) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];

        int res = 0;
        for (long low = x, high = x + 1; low <= sum[n]; low *= 10, high *= 10) {
            int[] cnt = new int[10];
            int l1 = 0, l2 = 0;
            for (long s : sum) {
                while (sum[l1] <= s - high) cnt[(int)(sum[l1++] % 10)]--;
                while (sum[l2] <= s - low) cnt[(int)(sum[l2++] % 10)]++;
                res += cnt[(int)((s - x + 10) % 10)];
            }
        }
        return res;
    }
}