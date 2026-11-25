class Solution {
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int max = 0;
        for (int n : arrivals) max = Math.max(max, n);

        int[] count = new int[max + 1];
        boolean[] discarded = new boolean[arrivals.length];

        int result = 0;
        for (int i = 0; i < arrivals.length; i++) {
            if (i - w >= 0 && discarded[i - w] == false) {
                count[arrivals[i - w]]--;
            }

            if (count[arrivals[i]] == m) {
                discarded[i] = true;
                result++;
            } else {
                count[arrivals[i]]++;
            }
        }

        return result;
    }
}