class Solution {
    public int countSpecialSubsequences(int[] nums) {
        long[] count = new long[4];
        count[0] = 1;
        for(int num : nums) count[1 + num] = (2 * count[1 + num] + count[num]) % 1000000007;
        return (int)count[3];
    }
}