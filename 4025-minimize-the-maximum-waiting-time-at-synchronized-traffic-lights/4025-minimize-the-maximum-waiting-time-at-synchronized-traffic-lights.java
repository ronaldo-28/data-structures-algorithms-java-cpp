class Solution {
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        int max = 0;
        for (int l : lights) {
            max = Math.max(l, max);
        }

        int res = 0;

        for (int a : arrivalTime) {
            if (a >= max) {
                int rem = a % period;
                if (rem >= max) {
                    res = Math.max(period - rem, res);
                }
            }
        }
        return res;
    }
}