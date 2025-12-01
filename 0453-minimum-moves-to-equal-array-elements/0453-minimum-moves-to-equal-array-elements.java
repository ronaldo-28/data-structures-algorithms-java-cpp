class Solution {
    static {
        int[] n = {1,2,1};
        for(int i=0;i<250;i++) minMoves(n);
    }

    public static int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length, sum = 0, min = nums[0];
        for (int a: nums) {
            sum += a;
            if (a < min) {
                min = a;
            }
        }
        return (int)(sum - (long)n * min);
    }
}