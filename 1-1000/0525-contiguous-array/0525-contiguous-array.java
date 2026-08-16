class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] f = new int[2*n+1];
        for (int i = 0; i < f.length; i++) {
            f[i] = -2;
        }
        f[n] = -1;

        int maxlen = 0, prefixSum = n;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i] * 2 - 1;
            if (f[prefixSum] == -2) {
                f[prefixSum] = i;
            } else {
                maxlen = Math.max(maxlen, i - f[prefixSum]);
            }
        }

        return maxlen;
    }
}