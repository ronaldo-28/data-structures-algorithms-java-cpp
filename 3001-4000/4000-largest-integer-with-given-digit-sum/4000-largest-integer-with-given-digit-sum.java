class Solution {
    private static final int[] pow = {0, 1, 10, 100, 1000, 10000, 100000};
    public int largestInteger(int n, int s) {
        if (s > n * 9) return -1;
        int q = s / 9;
        return pow[n + 1] - pow[n - q + 1] + (s % 9) * pow[n - q];
    }
}