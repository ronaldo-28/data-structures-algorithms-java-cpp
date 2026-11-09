class Solution {
    static final int MOD = 1_000_000_007;

    public int countNumbers(String l, String r, int b) {
        int[] lo = toBase(l, b);
        int[] hi = toBase(r, b);
        long ans = countUpTo(hi, b) - countUpTo(lo, b) + (isNonDecreasing(lo) ? 1 : 0);
        return (int) (((ans % MOD) + MOD) % MOD);
    }

    int[] toBase(String s, int b) {
        int[] dec = new int[s.length()];
        for (int i = 0; i < dec.length; i++) {
            dec[i] = s.charAt(i) - '0';
        }
        List<Integer> out = new ArrayList<>();
        int start = 0;
        while (start < dec.length) {
            int rem = 0;
            for (int i = start; i < dec.length; i++) {
                int cur = rem * 10 + dec[i];
                dec[i] = cur / b;
                rem = cur % b;
            }
            out.add(rem);
            while (start < dec.length && dec[start] == 0) {
                start++;
            }
        }
        if (out.isEmpty()) {
            out.add(0);
        }
        int[] digits = new int[out.size()];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = out.get(digits.length - 1 - i);
        }
        return digits;
    }

    boolean isNonDecreasing(int[] d) {
        for (int i = 1; i < d.length; i++) {
            if (d[i] < d[i - 1]) {
                return false;
            }
        }
        return true;
    }

    long countUpTo(int[] d, int b) {
        int len = d.length;
        long[][] ways = new long[len + 1][b + 1];
        for (int last = 0; last < b; last++) {
            ways[0][last] = 1;
        }
        for (int rem = 1; rem <= len; rem++) {
            for (int last = b - 1; last >= 0; last--) {
                ways[rem][last] = (ways[rem][last + 1] + ways[rem - 1][last]) % MOD;
            }
        }
        long res = 0;
        int last = 0;
        for (int pos = 0; pos < len; pos++) {
            for (int dg = last; dg < d[pos]; dg++) {
                res += ways[len - pos - 1][dg];
            }
            if (d[pos] < last) {
                return res % MOD;
            }
            last = d[pos];
        }
        return (res + 1) % MOD;
    }
}
