class Solution {
    public boolean xorGame(int[] nums) {
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }
        if (xorSum == 0) {
            return true;
        }
        if (nums.length % 2 == 0) {
            return true;
        }
        return false;
    }
}