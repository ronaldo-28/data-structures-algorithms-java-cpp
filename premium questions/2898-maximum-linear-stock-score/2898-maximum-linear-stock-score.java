class Solution {
    public long maxScore(int[] nums) {
        // group by differance 
        // prices[indexes[j]] - indexes[j] ==  prices[indexes[j - 1]] - indexes[j - 1]
        HashMap<Integer, Long> map = new HashMap<>();
        long bestSoFar = 0;
        for (int i = 0; i < nums.length; i++) {
            int day = i+1;
            int sum = nums[i]-day;

            long val = map.getOrDefault(sum, 0L) + nums[i];

            map.put(sum, val);
            bestSoFar = Math.max(bestSoFar, val);
        }
        return bestSoFar;
    }
}