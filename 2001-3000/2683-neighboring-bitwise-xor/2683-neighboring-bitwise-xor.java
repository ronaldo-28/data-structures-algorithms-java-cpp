class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int xorSum = 0;
        for (int val : derived) {
            xorSum ^= val;
        }
        return xorSum == 0;
    }
}