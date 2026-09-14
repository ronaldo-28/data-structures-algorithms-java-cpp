class Solution {

    /*

        best of 3 recursive calls

        take first 2
        take last 2
        take first and last

        keep track start/end inidices

        take the max of all calls

        2 4 1 5 6
        0 1 2 3 4

        s   e

        e - s = 2

        while (e - s) >= 2


        can we memoize?
        yes, using start and end idx

        [start, end] -> result
        18
        6

        12
        totalSum = sum(nums) - sum(remaining elements)
        totalSum = sum(nums) - sum(elements NOT chosen in nums)


    */
    record Range(int start, int end) {};

    public int maxScore(int[] nums) {
        int minElt = Integer.MAX_VALUE;
        int n = nums.length - 1;
        int totalSum = 0;
                
        if (nums.length % 2 == 0) {
            int currentAdjSum = 0;
            int minSum = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                currentAdjSum = nums[i] + nums[i + 1];
                minSum = Math.min(minSum, currentAdjSum);
                totalSum += nums[i];
            }
            totalSum += nums[n];
            totalSum -= minSum;
        }
        else {            
            for (int i = 0; i < nums.length; i++) {
                minElt = Math.min(nums[i], minElt);
                totalSum += nums[i];
            }
            totalSum -= minElt;
        }

        return totalSum;
    }


    // not used
    private int maxScore(Map<Range, Integer> memo, int start, int end, int[] nums) {
        if (end - start <= 2) {
            return 0;
        }
        Range rangeKey = new Range(start, end);
        if (memo.containsKey(rangeKey)) {
            return memo.get(rangeKey);
        }

        // take first two
        int firstTwoSum = nums[start] + nums[start + 1] + maxScore(memo, start + 2, end, nums);

        // take last two
        int lastTwoSum = nums[end - 1] + nums[end - 2] + maxScore(memo, start, end - 2, nums);

        // take fist and last
        int firstAndLastSum = nums[start] + nums[end - 1] + maxScore(memo, start + 1, end - 1, nums);

        int maxSum = Math.max(Math.max(firstTwoSum, lastTwoSum), firstAndLastSum);
        memo.put(new Range(start, end), maxSum);

        return maxSum;
    }
}