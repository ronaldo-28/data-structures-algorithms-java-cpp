class Solution {
    public int maxSubarrayLength(int[] nums) {
        int n = nums.length;
        
        int[] rightMin = new int[n];
        rightMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], nums[i]);
        }
        
        int res = 0;
        for (int i = 0, j = 0; i < n && j < n; i++) {
            j = Math.max(i, j);
            while (j < n && rightMin[j] < nums[i]) {
                j++;
            }
            res = Math.max(res, j - i);
        }
        return res;
    }
}