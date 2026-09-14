class Solution {

    static class Line {
        long m;
        long b;
        int c;

        Line(long m, long b, int c) {
            this.m = m;
            this.b = b;
            this.c = c;
        }
    }

    static class Result {
        long dp;
        int cnt;

        Result(long dp, int cnt) {
            this.dp = dp;
            this.cnt = cnt;
        }
    }

    private long[] pref;
    private int n;

    public long minPartitionScore(int[] nums, int k) {
        n = nums.length;

        pref = new long[n + 1];

        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }

        long total = pref[n];

        long lo = -total * total;
        long hi = total * total;

        while (lo < hi) {
            long mid = Math.floorDiv(lo + hi + 1, 2);

            if (run(mid).cnt >= k) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        Result result = run(lo);

        long res = result.dp - lo * k;

        return Math.round((res + total) / 2.0);
    }

    private Result run(long penalty) {
        long[] dp = new long[n + 1];
        int[] cnt = new int[n + 1];

        Line[] hull = new Line[n + 2];

        int head = 0;
        int tail = 0;

        hull[tail++] = new Line(0, 0, 1);

        for (int i = 1; i <= n; i++) {
            long x = pref[i];

            while (tail - head >= 2
                    && !better(hull[head], hull[head + 1], x)) {
                head++;
            }

            Line best = hull[head];

            dp[i] = value(best, x) + x * x + penalty;
            cnt[i] = best.c;

            Line newLine = new Line(
                    -2L * x,
                    dp[i] + x * x,
                    cnt[i] + 1
            );

            while (tail - head >= 2
                    && bad(hull[tail - 2], hull[tail - 1], newLine)) {
                tail--;
            }

            hull[tail++] = newLine;
        }

        return new Result(dp[n], cnt[n]);
    }

    private long value(Line line, long x) {
        return line.m * x + line.b;
    }

    private boolean better(Line a, Line b, long x) {
        long va = value(a, x);
        long vb = value(b, x);

        return va < vb || (va == vb && a.c > b.c);
    }

    private boolean bad(Line a, Line b, Line c) {
        return (b.b - a.b) * (b.m - c.m)
                >= (c.b - b.b) * (a.m - b.m);
    }
}