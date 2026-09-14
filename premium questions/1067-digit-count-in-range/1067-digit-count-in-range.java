class Solution {
    public int digitsCount(int d, int low, int high) {
        return digitsCount(d, high) - digitsCount(d, low - 1);
    }
    private int digitsCount(int d, int n) {
        if (n < 0 || n < d) {
            return 0;
        }
        int count = 0;
        for (long i = 1; i <= n ; i *= 10) {
            long divisor = i * 10;
            count += (n / divisor) * i;
            if (d > 0) {
                count += Math.min(Math.max(n % divisor - d * i + 1, 0), i);
            } else {
                if (n / divisor > 0) {
                    if (i > 1) {
                        count -= i;
                        count += Math.min(n % divisor + 1, i);
                    }
                }
            }
        }
        return count;
    }
}