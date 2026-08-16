class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long sum = 0L;
        for (int i = 0; i <= r; i++) {
            sum += stations[i];
        }
        long[] power = new long[n];
        power[0] = sum;
        long minPower = sum;
        for (int i = 1; i < n; i++) {
            if (i + r < n) sum += stations[i + r];
            if (i - r - 1 >= 0) sum -= stations[i - r - 1];
            power[i] = sum;
            minPower = Math.min(minPower, power[i]);
        }
        // System.out.println(Arrays.toString(power));
        long L = minPower;
        long R = minPower + k;
        outer: while (L < R) {
            long m = L + (R - L + 1) / 2;
            // System.out.printf("L = %d, R = %d, m = %d%n", L, R, m);
            int left = k;
            long[] wasted = new long[n];
            int touchingHere = 0;
            for (int i = 0; i < n; i++) {
                if (i - 2 * r - 1 >= 0) touchingHere -= wasted[i - 2 * r - 1];
                if (power[i] + touchingHere < m) {
                    wasted[i] = m - power[i] - touchingHere;
                    touchingHere += wasted[i];
                    left -= wasted[i];
                    if (left < 0) {
                        R = m - 1;
                        continue outer;
                    }
                }
            }
            L = m;
        }
        return L;
    }
}