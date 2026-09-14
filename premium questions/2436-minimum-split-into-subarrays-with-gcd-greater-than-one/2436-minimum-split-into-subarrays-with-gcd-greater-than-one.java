class Solution {
    public int minimumSplits(int[] nums) {
        int sub = 1, gcd = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int a = nums[i];
            gcd = gcd(gcd, a);
            if (gcd == 1) {
                sub++;
                gcd = nums[i];
            }
        }
        return sub;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}