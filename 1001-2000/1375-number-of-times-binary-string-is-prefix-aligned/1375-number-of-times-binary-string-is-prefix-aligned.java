class Solution {
    public int numTimesAllBlue(int[] flips) {
        int ans = 0, mx = 0;
        int n_flips = flips.length;
        for (int i = 1; i <= n_flips; ++i) {
            mx = Math.max(mx, flips[i - 1]);
            if (mx == i) {
                ++ans;
            }
        }
        return ans;
    }
}