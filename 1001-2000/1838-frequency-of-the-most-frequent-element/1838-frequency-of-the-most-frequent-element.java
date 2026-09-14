class Solution {
   
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        int index = 0;

        for (int num : nums)
            max = Math.max(num, max);
            
        int[] count = new int[max + 1];
        for (int num : nums) count[num]++;
        for (int i = 0; i <= max; i++)
            while (count[i]-- > 0)
                nums[index++] = i;

        int left = 0;
        long curr = 0;
        
        for (int right = 0; right < nums.length; right++) {
            long target = nums[right];
            curr += target;
            
            if ((right - left + 1) * target - curr > k) {
                curr -= nums[left];
                left++;
            }
        }
        
        return nums.length - left;
    }
}