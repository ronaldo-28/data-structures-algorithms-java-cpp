class Solution {

    public long findMaximumNumber(long k, int x) {

        long low = 1;
        long high = (long) 1e15;
        long ans = 0;

        while (low <= high) {

            long mid = low + (high - low) / 2;

            if (check(mid, x, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(long mid, int x, long k) {

        long sum = 0;

        for (int bit = x - 1; bit < 60; bit += x) {

            sum += count(mid, bit);

            if (sum > k)
                return false;
        }

        return true;
    }

    private long count(long n, int bit) {

        long block = 1L << bit;

        long cycle = block << 1;

        long full = (n + 1) / cycle;

        long rem = (n + 1) % cycle;

        return full * block + Math.max(0L, rem - block);
    }
}