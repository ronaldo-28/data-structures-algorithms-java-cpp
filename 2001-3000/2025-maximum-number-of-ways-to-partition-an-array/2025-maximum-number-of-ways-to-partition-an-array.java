class Solution {
    public int waysToPartition(int[] nums, int k) {
        int len = nums.length, total = 0, prefix = 0;
        HashMap<Integer, Integer> before = new HashMap<>(), after = new HashMap<>();

        for (int t : nums) total += t;

        for (int i = 0; i + 1 < len; i++){
            prefix += nums[i];
            after.put(prefix, after.getOrDefault(prefix, 0) + 1);
        }

        int max = (total & 1) == 0 ? after.getOrDefault(total / 2, 0) : 0;
        prefix = 0;

        for (int i = 0; i < len; i++){
            int diff = k - nums[i], target = total + diff;
            prefix += nums[i];

            if ((target & 1) == 0) max = Math.max(max, before.getOrDefault((target / 2), 0) +
            after.getOrDefault((target / 2) - diff, 0));
            
            if (i == len - 1) break;
            
            before.put(prefix, before.getOrDefault(prefix, 0) + 1);
            after.put(prefix, after.get(prefix) - 1);
        }

        return max;
    }
}