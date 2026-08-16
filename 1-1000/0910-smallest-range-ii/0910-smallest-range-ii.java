class Solution {
    public  int smallestRangeII(int[] nums, int k) {
        int max = 0;
        for ( int num : nums ) max = Math.max(num, max);
        
        boolean[] exists = new boolean[max+1];
        for ( int num : nums ) exists[num] = true;

        int N = 0;
        for ( int num = 0; num <= max; num++ ) {
            if ( exists[num] ) nums[N++] = num;
        }
        int min = nums[0];

        int k2 = k << 1;


        int minDiff = max - min;
        min += k2;
        for ( int ii = 1; ii < N; ii++ ) {
            int newMax = Math.max(max, nums[ii-1] + k2);
            int newMin = Math.min(min, nums[ii]);

            minDiff = Math.min(minDiff, newMax - newMin);
// System.out.printf("ii=%d, new=%d, %d, minDiff=%d\n", ii, newMin, newMax, minDiff);
        }
        return minDiff;
    }
}