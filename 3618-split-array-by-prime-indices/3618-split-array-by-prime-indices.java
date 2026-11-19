class Solution {
    public long splitArray(int[] nums) {
long sumA = 0;
        long sumB = 0;

        if(nums.length >= 1) sumB += nums[0];
        if(nums.length >= 2) sumB += nums[1];
        if(nums.length > 2) {
            int j = 2;
            do {
                sumA += nums[j];
                for (int k = j + j; k < nums.length; k += j) {
                    sumB += nums[k];
                    nums[k] = 0;
                }
                do {
                    j++;
                } while (j < nums.length && nums[j] == 0);
            } while (j * j < nums.length);
            while(j < nums.length) {
                sumA += nums[j];
                j++;
            }
        }
        return Math.abs(sumA - sumB); 
    }
}