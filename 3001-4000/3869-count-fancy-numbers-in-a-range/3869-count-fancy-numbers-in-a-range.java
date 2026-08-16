import java.util.*;

class Solution {
    static final int MAX_LEN = 16;
    static final int MAX_SUM = 9 * MAX_LEN;

    static boolean inited = false;
    static boolean[] goodSum;
    static long[][] memo;
    static long[] goodNumbers;
    static long[] goodNumbersAndGoodSum;

    public long countFancy(long l, long r) {
        init();
        return fancyUpTo(r) - fancyUpTo(l - 1);
    }

    static void init() {
        if (inited)
            return;
        inited = true;

        goodSum = new boolean[MAX_SUM + 1];
        memo = new long[MAX_LEN + 1][MAX_SUM + 1];

        for (int s = 0; s <= MAX_SUM; s++)
            goodSum[s] = isGoodInt(s);

        for (int sum = 0; sum <= MAX_SUM; sum++) {
            memo[MAX_LEN][sum] = goodSum[sum] ? 1L : 0L;
        }
        for (int pos = MAX_LEN - 1; pos >= 0; pos--) {
            for (int sum = 0; sum <= MAX_SUM; sum++) {
                long res = 0L;
                for (int d = 0; d <= 9; d++) {
                    if (sum + d <= MAX_SUM)
                        res += memo[pos + 1][sum + d];
                }
                memo[pos][sum] = res;
            }
        }

        HashSet<Long> set = new HashSet<>();
        set.add(0L);

        for (int mask = 1; mask < (1 << 9); mask++) {
            long num = 0L;
            for (int digit = 1; digit <= 9; digit++) {
                if ((mask & (1 << (digit - 1))) != 0)
                    num = num * 10 + digit;
            }
            set.add(num);
        }

        for (int mask = 1; mask < (1 << 10); mask++) {
            long num = 0L;
            for (int digit = 9; digit >= 0; digit--) {
                if ((mask & (1 << digit)) != 0)
                    num = num * 10 + digit;
            }
            set.add(num);
        }

        goodNumbers = new long[set.size()];
        int idx = 0;
        for (long v : set)
            goodNumbers[idx++] = v;
        Arrays.sort(goodNumbers);

        long[] tmp = new long[goodNumbers.length];
        int j = 0;
        for (long v : goodNumbers) {
            if (goodSum[digitSum(v)])
                tmp[j++] = v;
        }
        goodNumbersAndGoodSum = Arrays.copyOf(tmp, j);
    }

    static long fancyUpTo(long n) {
        if (n < 0)
            return 0L;
        long a = countGoodDigitsUpTo(n);
        long b = countGoodDigitSumUpTo(n);
        long c = countGoodDigitsAndGoodSumUpTo(n);
        return a + b - c;
    }

    static long countGoodDigitsUpTo(long n) {
        return upperBound(goodNumbers, n);
    }

    static long countGoodDigitsAndGoodSumUpTo(long n) {
        return upperBound(goodNumbersAndGoodSum, n);
    }

    static long countGoodDigitSumUpTo(long n) {
        int[] digits = new int[MAX_LEN];
        long x = n;
        for (int i = MAX_LEN - 1; i >= 0; i--) {
            digits[i] = (int) (x % 10);
            x /= 10;
        }

        long res = 0L;
        int sum = 0;
        for (int pos = 0; pos < MAX_LEN; pos++) {
            int limit = digits[pos];
            for (int d = 0; d < limit; d++)
                res += memo[pos + 1][sum + d];
            sum += limit;
        }
        if (goodSum[sum])
            res++;
        return res;
    }

    static int upperBound(long[] arr, long x) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= x)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }

    static int digitSum(long x) {
        int s = 0;
        while (x > 0) {
            s += (int) (x % 10);
            x /= 10;
        }
        return s;
    }

    static boolean isGoodInt(int x) {
        if (x < 10)
            return true;

        int[] ds = new int[10];
        int len = 0;
        while (x > 0) {
            ds[len++] = x % 10;
            x /= 10;
        }

        boolean inc = true, dec = true;
        for (int i = len - 1; i > 0; i--) {
            int a = ds[i];
            int b = ds[i - 1];
            if (b <= a)
                inc = false;
            if (b >= a)
                dec = false;
        }
        return inc || dec;
    }
}