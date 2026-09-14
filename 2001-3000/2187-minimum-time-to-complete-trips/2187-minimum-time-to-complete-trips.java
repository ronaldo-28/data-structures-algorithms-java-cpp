class Solution {
    public long minimumTime(int[] time, int totalTrips) {

        long i = 1;
        long j = 100_000_000_000_000L;

        while (i <= j) {

            long mid = i + (j - i) / 2;

            if (check(time, totalTrips, mid)) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return i;
    }

    private static boolean check(int[] t, long tT, long mid) {

        long trip = 0;

        for (int i : t) {
            trip += mid / i;
            if (trip >= tT) return true;
        }
        return trip >= tT;
    }
}