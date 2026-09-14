class Solution {
    public long maximumSumScore(int[] nums) {
        long maxSumScore = Long.MIN_VALUE;
        long preFixSum = 0;
        for(int num : nums){
            preFixSum += num;
        }
        long curSum = 0;
        for(int num : nums){
            curSum += num;
            maxSumScore = Math.max(maxSumScore, Math.max(curSum, preFixSum));
            preFixSum -= num;
        }
        return maxSumScore;
    }
}