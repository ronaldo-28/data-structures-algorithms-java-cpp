class Solution {
    public int kEmptySlots(int[] bulbs, int k) {
        k++;
        int n = bulbs.length;
        int bucketCnt = (n - 1) / k + 1;

        int[] mins = new int[bucketCnt + 2];
        Arrays.fill(mins, 3 * n);
        int[] maxs = new int[bucketCnt + 2];
        Arrays.fill(maxs, -2 * n);
        for (int idx = 0; idx < n; idx++) {
            int bulb = bulbs[idx]-1;
            int bucket = bulb / k + 1;
            if (bulb < mins[bucket]) {
                mins[bucket] = bulb;
                if (bulb - maxs[bucket-1] == k) {
                    return idx+1;
                }
            }
            if (bulb > maxs[bucket]) {
                maxs[bucket] = bulb;
                if (mins[bucket+1] - bulb == k) {
                    return idx+1;
                }
            }
        }
        return -1;
    }
}