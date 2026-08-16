class Solution {
    static {
        int[] nums = {2, 1, 6, 4};
        for(int i = 0; i < 500; i++)
            waysToMakeFair(nums);
    }
    
    public static int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int totalEven = 0, totalOdd = 0;
        
        for(int i = 0; i < n; i++) {
            if((i & 1) == 0) totalEven += nums[i];
            else totalOdd += nums[i];
        }
        
        int count = 0;
        int leftEven = 0, leftOdd = 0;
        
        for(int i = 0; i < n; i++) {
            int val = nums[i];
            boolean isEven = (i & 1) == 0;
            
            int rightEven = isEven ? totalEven - leftEven - val : totalEven - leftEven;
            int rightOdd = isEven ? totalOdd - leftOdd : totalOdd - leftOdd - val;

            if(leftEven + rightOdd == leftOdd + rightEven) count++;
            
            if(isEven) leftEven += val;
            else leftOdd += val;
        }
        
        return count;
    }
}