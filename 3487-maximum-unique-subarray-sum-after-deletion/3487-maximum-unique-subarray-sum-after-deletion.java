class Solution {
    public int maxSum(int[] nums) {

        boolean hasPositive = false;
        int max = Integer.MIN_VALUE;
        boolean[] occur = new boolean[101];

        for (int n : nums) {
            max = Math.max(max, n);
            if (n > 0) {
                occur[n] = true;
                hasPositive = true;
            }
        }
        if (!hasPositive) return max;
        int ans = 0;
        for (int i = 0; i < 101; i++) {
            if (occur[i]) ans += i;
        }
        return ans;
    }
}