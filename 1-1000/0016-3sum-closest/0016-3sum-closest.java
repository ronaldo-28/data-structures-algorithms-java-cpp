class Solution {
    public int threeSumClosest(int[] nums, int target) {
        byte[] occurrences = new byte[2001];
        for (int num : nums)
            occurrences[num + 1000]++;
        for (int count = 0, index = 0; count < 2001; count++)
            for (int repetitions = occurrences[count]; repetitions-- > 0;)
                nums[index++] = count - 1000;
        int low = 0, high = nums.length - 1, closestSum = Integer.MAX_VALUE;
        for (int minDiff = target - nums[high - 1] - nums[high], end = high - 2; low < end
                && nums[low + 1] <= minDiff; low++)
            ;
        for (int maxDiff = target - nums[low] - nums[low + 1], end = low + 2; high > end
                && nums[high - 1] >= maxDiff; high--)
            ;
        for (int end = high - 1; low < end; low++)
            for (int sum, i = high, j = low + 1; j < i;)
                if ((sum = nums[low] + nums[i] + nums[j]) == target)
                    return sum;
                else if ((sum > target ? sum - target - i + i-- : target - sum - j + j++) < Math
                        .abs(closestSum - target))
                    closestSum = sum;
        return closestSum;
    }
}