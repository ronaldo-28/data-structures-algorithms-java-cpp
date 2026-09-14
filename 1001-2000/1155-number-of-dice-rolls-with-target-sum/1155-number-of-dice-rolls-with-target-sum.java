class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        if (!(n <= target && target <= n * k)) return 0;
        int res = 0, maxViolating = (target - n) / k;
        for (int b = 0; b <= maxViolating; b++) {
            int amt = mult(choose(n, b), choose(target - mult(b, k) - 1, n - 1));
            if (b % 2 == 0) res = plus(res, amt);
            else res = minus(res, amt);
        }
        return res;
    }
    static int choose(int n, int k) {
        if (n < 0 || k < 0) return 0;
        return mult(factorial(n), mult(inverse(factorial(k)), inverse(factorial(n - k))));
    }
    static int inverse(long v) {
        return pow(v, 1_000_000_005);
    }
    static int pow(long b, long p) {
        if (p == 0) return 1;
        long h = pow(b, p >> 1);
        if (p % 2 == 0) return mult(h, h);
        return mult(b, mult(h, h));
    }
    static int factorial(long n) {
        int r = 1;
        for (int v = 2; v <= n; v++) r = mult(r, v);
        return r;
    }
    static int mult(long a, long b) {
        return mod(mod(a) * (long) mod(b));
    }
    static int plus(long a, long b) {
        return mod(mod(a) + mod(b));
    }
    static int minus(long a, long b) {
        a = mod(a); b = mod(b);
        if (a >= b) return mod(a - b);
        return mod(a + 1_000_000_007 - b);
    }
    static int mod(long l) {
        return (int) (l % 1_000_000_007);
    }
}