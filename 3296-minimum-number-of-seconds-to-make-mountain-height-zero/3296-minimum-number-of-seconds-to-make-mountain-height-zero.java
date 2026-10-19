class Solution {

    int mx(int[] a) {
        int m = 0;
        for (int x : a) m = Math.max(m, x);
        return m;
    }

    boolean ok(int[] wt, int h, long t) {
        for (int w : wt) {
            h -= ((int)(-1 + Math.sqrt(1 + (t << 3) / w))) >> 1;
            if (h <= 0) return true;
        }
        return false;
    }

    public long minNumberOfSeconds(int h, int[] wt) {

        int mx = mx(wt);
        int avg = (h + wt.length - 1) / wt.length;

        long l = 1;
        long r = (long) mx * avg * (avg + 1) / 2;

        while (l <= r) {
            long mid = (l + r) >>> 1;

            if (ok(wt, h, mid))
                r = mid - 1;
            else
                l = mid + 1;
        }

        return l;
    }
}