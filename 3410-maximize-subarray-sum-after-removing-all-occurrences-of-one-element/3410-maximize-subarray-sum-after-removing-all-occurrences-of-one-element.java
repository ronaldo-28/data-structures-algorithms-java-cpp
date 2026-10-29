import java.util.HashMap;
import java.util.Map;

class Solution {
    public long maxSubarraySum(int[] nums) {
        Map<Integer, Long> map = new HashMap<>();
        map.put(0, 0L);

        long result = Long.MIN_VALUE;
        long prefixSum = 0;
        long lowest = 0;
        for (int num : nums) {
            prefixSum += num;
            result = Math.max(result, prefixSum - lowest);
            if (num < 0) {
                map.put(num, num + Math.min(map.getOrDefault(num, 0L), map.get(0)));
                lowest = Math.min(lowest, map.get(num));
            }
            long negativePrefixSum = map.get(0);
            map.put(0, Math.min(negativePrefixSum, prefixSum));
            lowest = Math.min(lowest, negativePrefixSum);
        }
        return result;
    }
}