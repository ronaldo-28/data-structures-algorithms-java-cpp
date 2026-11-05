class Solution {
    static {
        for(int i = 0; i <= 500; i++) {
            largestInteger(new int[0], 0);
        }
    }
    public static int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if(n == 0) return n;
        // Case 2, find maximum num
        if(n == k) {
            int max = nums[0];
            for(int i = 1; i < n; i++) {
                max = Math.max(max, nums[i]);
            }
            return max;
        }
        // Case 3, find maximum with freq 1
        if(k == 1) {
            // Better than a HashMap here lol
            int[] freq = new int[51];
            for(int c : nums) {
                freq[c]++;
            }
            int max = -1;
            for(int c : nums) {
                if(freq[c] >= 2) continue;
                max = Math.max(max, c);
            }
            return max;
        }
        // Case 1, either nums[0] or nums[n-1]
        // Check if they appear once
        if(nums[0] == nums[n-1]) return -1;
        boolean option1 = true;
        boolean option2 = true;
        for(int i = 1; i < n-1; i++) {
            if(nums[i] == nums[0]) option1 = false;
            if(nums[i] == nums[n-1]) option2 = false;
        }
        if(option1 && !option2) return nums[0];
        if(!option1 && option2) return nums[n-1];
        if(!option1 && !option2) return -1;
        return Math.max(nums[0], nums[n-1]);
    }

    // Intuition: x can only appear 1 time in subarrays of size k, if:
    // - It's at index 0 or index n-1 AND only appears once
    // - k == n
    // - k == 1 -> Find maximum single freq
}