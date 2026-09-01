class Solution {
    public boolean isConsecutive(int[] nums) {
        int min = Integer.MAX_VALUE;
        boolean[] seen = new boolean[nums.length];
        for (int num : nums) {
            min = Math.min(min, num);
        }
        for (int num : nums) {
            int idx = num - min;
            if (idx < 0 || idx >= seen.length || seen[idx]) return false;
            seen[idx] = true;
        }
        return true;
    }
}