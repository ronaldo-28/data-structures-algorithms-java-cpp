class Solution {
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int max = 0; 
        for (int num : nums) max = Math.max(max, num);
        int mask = Integer.highestOneBit(max); 

        int prefixMask = 0; 
        int answer = 0; 

        while (mask != 0) {
            prefixMask |= mask; 
            int candidate = answer | mask; // tentatively set this bit in answer to 1 
            if (canXor(nums, prefixMask, candidate)) {
                answer = candidate; 
            } 

            mask >>= 1; 
        } 

        return answer; 
    }

    private boolean canXor(int[] nums, int prefixMask, int candidate) {
        HashSet<Integer> seen = new HashSet<>(nums.length); 
        for (int num : nums) {
            int prefix = num & prefixMask; 
            if (seen.contains(prefix ^ candidate)) {
                return true;
            }

            seen.add(prefix); 
        }

        return false; 
    }
}