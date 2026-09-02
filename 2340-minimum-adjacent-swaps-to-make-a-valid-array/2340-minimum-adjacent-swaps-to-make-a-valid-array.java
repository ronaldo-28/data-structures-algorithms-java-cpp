class Solution {
    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return 0;
        }
        int maxInd = -1, maxVal = -1;
        int minInd = -1, minVal = Integer.MAX_VALUE;
        int numSwaps = 0;

        for(int i = 0; i < n; i++) {
            if(nums[i] >= maxVal) {
                maxInd = i;
                maxVal = nums[i];
            }

            if(nums[i] < minVal) {
                minInd = i;
                minVal = nums[i];
            }
        }

        if(minInd < maxInd) {
            numSwaps = minInd + (n-maxInd-1);
        } else {
            numSwaps = n-maxInd-1;
            numSwaps += minInd - 1;
        }

        return numSwaps;
    }
}