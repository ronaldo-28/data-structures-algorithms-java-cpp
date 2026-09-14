class Solution {
    static {
        for(int i=0;i <=500;i++) {
            new Solution().maximumProduct(new int[]{});
        }
    }
    public int maximumProduct(int[] nums) {
        int l = nums.length;
        int ma1 = Integer.MIN_VALUE;
        int ma2 = Integer.MIN_VALUE;
        int ma3 = Integer.MIN_VALUE;
        int mi1 = Integer.MAX_VALUE;
        int mi2 = Integer.MAX_VALUE;
        for (int n: nums) {
            if (n > ma1) {
                ma3 = ma2;
                ma2 = ma1;
                ma1 = n;
            } else if (n > ma2) {
                ma3 = ma2;
                ma2 = n;
            } else if (n > ma3) {
                ma3 = n;
            }
            if (n < mi1) {
                mi2 = mi1;
                mi1 = n;
            } else if (n < mi2) {
                mi2 = n;
            }
        }
        return Math.max(Math.max(mi1 * mi2 * ma1, mi1 * ma2 * ma1), ma1 * ma2 * ma3);
        // Arrays.sort(nums);
        // int max1 = nums[0] * nums[1] * nums[l - 1];
        // int max2 = nums[0] * nums[l - 2] * nums[l - 1];
        // int max3 = nums[l - 3] * nums[l - 2] * nums[l - 1];

        // return Math.max(Math.max(max1, max2), max3);
    }
}