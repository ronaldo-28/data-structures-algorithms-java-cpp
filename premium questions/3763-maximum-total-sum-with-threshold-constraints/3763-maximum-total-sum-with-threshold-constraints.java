class Solution {
    public long maxSum(int[] nums, int[] threshold) {
        int n = threshold.length;
        Integer[] map = new Integer[n];
        for (int i = 0; i < map.length; i++) {
            map[i] = i;
        }
        Arrays.sort(map, (a, b) -> Integer.compare(threshold[a], threshold[b]));
        long result = 0L;
        int idx = 0;
        for (int step = 1; step <= n; step++) {
            idx = map[step - 1];
            if (threshold[idx] <= step) {
                result += nums[idx];
            } else {
                break;
            }
        }
        return result;
    }
}