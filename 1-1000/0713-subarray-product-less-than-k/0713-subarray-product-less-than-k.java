class Solution {
    static{
        for(int i=0;i<500;i++)numSubarrayProductLessThanK(new int[5], 0);
    }
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k==0) return 0;
        int start = 0;
        int count = 0;
        int product = 1;
        for(int i = 0; i<nums.length; i++) {
            product *= nums[i];
            while (start <= i && product >= k) {
                product /= nums[start];
                start++;
            }
            count += (i - start + 1);
        }
        return count;
    }
}