class Solution {
    public int maxScore(int[] nums) {
        int score = 0, max = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            max = Math.max(nums[i], max);
            score += max;
        }
        return score;
    }
}