class Solution {
    public int[] maximumLengthOfRanges(int[] nums) {
        int n = nums.length, top = 0;
        int[] stack = new int[n + 1], ans = new int[n];
        stack[0] = -1;
        for(int i = 0; i < n; i++) {
            while(top != 0 && nums[i] > nums[stack[top]]) ans[stack[top--]] = i - stack[top] - 1;
            stack[++top] = i;
        }
        while(top != 0) ans[stack[top--]] = n - stack[top] - 1;
        return ans;
    }
}