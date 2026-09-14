class Solution {
    static {
        for(int i = 0; i < 500; i++)
            smallerNumbersThanCurrent(null);
    }
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] freq = new int[101]; 
        if(nums == null) return new int[]{};
        for (int num : nums) freq[num]++;

        int[] prefix = new int[101];    
        prefix[0] = freq[0];
        for (int i = 1 ; i<101 ; i++) {
            prefix[i] = prefix[i-1] + freq[i];
        }

        int[] result = new int[nums.length]; 
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                result[i] = 0;
            else
                result[i] = prefix[nums[i] - 1];
        }

        return result;
    }
}