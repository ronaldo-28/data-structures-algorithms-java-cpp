class Solution {
    public int countTriples(int n) {
        int ans = 0;
        for (int u = 3; u * u < n * 2; u += 2) {
            for (int v = 1; v < u && u * u + v * v <= n * 2; v += 2) {
                if (gcd(u, v) == 1) {
                    ans += n * 2 / (u * u + v * v);
                }
            }
        }
        return ans * 2; // (a,b,c) 和 (b,a,c) 各算一次
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}