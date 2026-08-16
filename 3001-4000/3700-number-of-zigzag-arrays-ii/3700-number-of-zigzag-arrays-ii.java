class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int len = r - l + 1;
        long[][] m1 = new long[len][len];
        long[][] m2 = new long[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                m1[i][j] = 1;
            }
            for (int j = 0; j < i; j++) {
                m2[i][j] = 1;
            }
        }
        long[][] m = pro(m1, m2);
        long[] arr = new long[len];
        Arrays.fill(arr, 1);
        n--;
        int count = n / 2;
        while (count > 0) {
            if (count % 2 == 1)
                arr = pro(arr, m);
            m = pro(m);
            count /= 2;
        }
        if (n % 2 == 1)
            arr = pro(arr, m1);
        long res = 0;
        for (long num : arr) {
            res += num;
        }
        return (int) (res * 2 % mod);
    }

    int mod = 1_000_000_007;

    public long[][] pro(long[][] a) {
        long[][] res = new long[a.length][a[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int k = 0; k < res.length; k++) {
                if (a[i][k] == 0)
                    continue;
                for (int j = 0; j < res.length; j++) {
                    res[i][j] = (res[i][j] + a[i][k] * a[k][j]) % mod;
                }
            }
        }
        return res;
    }

    public long[][] pro(long[][] a, long[][] b) {
        long[][] res = new long[a.length][a[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int k = 0; k < res.length; k++) {
                if (a[i][k] == 0)
                    continue;
                for (int j = 0; j < res.length; j++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return res;
    }

    public long[] pro(long[] a, long[][] b) {
        long[] res = new long[a.length];
        for (int j = 0; j < res.length; j++) {
            if (a[j] == 0)
                continue;
            for (int i = 0; i < res.length; i++) {
                res[i] = (res[i] + a[j] * b[j][i]) % mod;
            }
        }
        return res;
    }
}