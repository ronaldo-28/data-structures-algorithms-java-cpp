class Solution {
    private int nbrOfSubArraysWithSumLessThanX(int[] nums, int x) {
        int count = 0, sum = 0, start = 0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
            while (sum > x) {
                sum -= nums[start];
                start ++;
            }
            // subarray sum between i & start should be < x 
            count += i - start + 1;
        }
        
        return count;
    }
    
    public int kthSmallestSubarraySum(int[] nums, int k) {
        int s = 0, e = sumArray(nums);
        
        while (s <= e) {
            int mid = (s + e) / 2;
            if (nbrOfSubArraysWithSumLessThanX(nums, mid) < k) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        
        return s;
    }
    
    private int sumArray(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i ++)
            sum += nums[i];
        
        return sum;
    }
}