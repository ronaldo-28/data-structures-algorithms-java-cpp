class Solution {
    private int binExp(int a, int b, int M) {
        int res = 1;
        a %= M;

        while (b > 0) {
            if ((b & 1) != 0)
                res = (res * a) % M;
            a = (a * a) % M;
            b >>= 1;
        }

        return res;
    }

    public int superPow(int a, int[] b) {
        int m = 1140;
        int exp = 0;

        for (int i = 0; i < b.length; i++)
            exp = (exp * 10 + b[i]) % m;
        if (exp == 0)
            exp = 1140;

        return binExp(a, exp, 1337);
    }
}