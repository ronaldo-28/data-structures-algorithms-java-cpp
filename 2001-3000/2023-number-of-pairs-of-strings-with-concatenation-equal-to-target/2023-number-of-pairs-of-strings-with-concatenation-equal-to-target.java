class Solution {
    public int numOfPairs(String[] nums, String target) {
        int n = nums.length, m = target.length();
        int[] freq = new int[m + 1];
        for(int i = 0; i < n; i++) {
            if(target.endsWith(nums[i])) freq[m - nums[i].length()]++;
        }
        if((m & 1) == 0 && target.substring(0, m / 2).equals(target.substring(m / 2))) freq[m / 2]--;
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(target.startsWith(nums[i])) count += freq[nums[i].length()];
        }
        return count;
    }
}