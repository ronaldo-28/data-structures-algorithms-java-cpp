class Solution {
    public long maxScore(int[] a, int[] b) {
        long x0 = Long.MIN_VALUE / 2;
        long x1 = Long.MIN_VALUE / 2;
        long x2 = Long.MIN_VALUE / 2;
        long x3 = Long.MIN_VALUE / 2;

        for (int v : b) {
            x3 = Math.max(x3, x2 + (long) a[3] * v);
            x2 = Math.max(x2, x1 + (long) a[2] * v);
            x1 = Math.max(x1, x0 + (long) a[1] * v);
            x0 = Math.max(x0, (long) a[0] * v);
        }

        return x3;
    }
}