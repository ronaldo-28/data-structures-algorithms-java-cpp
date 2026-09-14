class Solution {
    // time = O(n), space = O(1)
    public int subsequenceCount(int[] nums) {
        int n = nums.length;
        long mod = (long)(1e9 + 7);
        long odd = 0, even = 0;

        for (int i = 0; i < n; i++) {
            long x = odd, y = even;
            if (nums[i] % 2 == 1) {
                odd = (x + y + 1) % mod;
                even = (y + x) % mod;
            } else {
                odd = x * 2 % mod;
                even = (y * 2 + 1) % mod;
            }
        }
        return (int)odd;
    }
}