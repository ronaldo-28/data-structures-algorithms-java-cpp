class Solution {
    public long maximumValue(int n, int s, int m) {
        long ans = s;

        if (n == 1) {
            return ans;
        }

        long add = 1L * m * (n / 2);
        ans += add;
        ans -= (n / 2 - 1);

        return ans;
    }
}