class Solution {
    public boolean nimGame(int[] piles) {
        int res = 0;
        for (int x : piles)  res ^= x;
        return res != 0;
    }
}