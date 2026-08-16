class Solution {
    public int scoreDifference(int[] nums) {
        int first = 0, second = 0;
        boolean isFirst = true;
        for(int i = 0; i < nums.length; i++) {
            int n = nums[i];
            
            // Toggle based on odd number
            if(n % 2 == 1) isFirst = !isFirst;
            // Toggle every 6th element
            if(i % 6 == 5) isFirst = !isFirst;

            if(isFirst) first += nums[i];
            else second += nums[i];
        }
        return first - second;
    }
}