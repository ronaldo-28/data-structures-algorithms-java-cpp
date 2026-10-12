class Solution {
    public int resilientSubarray(int[] nums, int k) {
        int n = nums.length, best = 0;
        for (int i = 0; i < n; ) {
            int rem = nums[i] % k, j = i;
            while (j < n && nums[j] % k == rem) j++;  // maximal run of one remainder: [i, j)
            int length = j - i, step = k / gcd(rem, k);  // the sum's remainder comes back every `step` elements
            best = Math.max(best, 1 + (length - 1) / step * step);  // longest L <= length with L ≡ 1 (mod step)
            i = j;
        }
        return best;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}