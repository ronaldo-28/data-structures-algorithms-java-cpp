class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int balance = nums.length;
        final int[] f = new int[nums.length * 2 + 1];
        long r = 0;
        f[balance] = 1;
        int current = 0;
        for (int n : nums) {
            if (n == target) {
                current += f[balance++];
            } else {
                current -= f[--balance];
            }
            r += current;
            f[balance]++;
        }
        return r;
    }
}