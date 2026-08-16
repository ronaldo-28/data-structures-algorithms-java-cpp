class Solution {
    public long countExcellentPairs(int[] nums, int k) {
        Set<Integer> distinct = new HashSet<>();
        int[] bitCount = new int[31];
        for(int i : nums) {
            if(distinct.add(i)) {
                bitCount[Integer.bitCount(i)]++;
            }
        }
        int[] sum = new int[31];
        for(int i = 30; i >= 0; i--) {
            sum[i] = bitCount[i] + (i == 30 ? 0 : sum[i + 1]);
        }
        long ans = 0;
        for(int i = Math.max(1, k - 30); i <= 30; i++) {
            ans += ((long) bitCount[i]) * sum[Math.max(0, k - i)];
        }
        return ans;
    }
}