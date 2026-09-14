class Solution {
    public long maxGcdSum(int[] nums, int k) {
        int[] indexes = new int[nums.length];
        int[] gcds = new int[nums.length];
        long[] sums = new long[nums.length];
        int idx = 0;
        long res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < idx; j++) {
                gcds[j] = GCD(gcds[j], nums[i]);
                sums[j] += nums[i];
            }
            indexes[idx] = i;
            gcds[idx] = nums[i];
            sums[idx] = nums[i];
            idx++;
            int idxNext = 0;
            for (int j = 0; j < idx; j++) {
                if (j == 0 || gcds[j] != gcds[j - 1]) {
                    indexes[idxNext] = indexes[j];
                    gcds[idxNext] = gcds[j];
                    sums[idxNext] = sums[j];
                    idxNext++;
                    if (i - indexes[j] + 1 >= k && res < sums[j] * gcds[j])
                        res = sums[j] * gcds[j];
                }
            }
            idx = idxNext;
        }
        return res;
    }

    public int GCD(int a, int b) {
        if (b == 0)
            return a;
        return GCD(b, a % b);
    }
}