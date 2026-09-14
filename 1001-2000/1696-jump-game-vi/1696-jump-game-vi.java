class Solution {
     public int maxResult(int[] nums, int k) {
         int n = nums.length, i, j, start = 0;
        int sum = nums[0];

        for(i=1; i<n; i++){
            nums[i] += nums[start];
            if(nums[i] >= nums[start]) start = i;

            if(i-start == k){
                start++;
                for(j = start+1; j<=i; j++){
                    if(nums[j] >= nums[start]) start = j;
                }
            }
        }

        return nums[n-1];
        
    }
}