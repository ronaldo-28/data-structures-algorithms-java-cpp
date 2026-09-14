class Solution {
    static {
        for (int i = 0; i < 100; i++) {
            stoneGameVIII(new int[]{});
        }
    }

    public static int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += stones[i];
        }
        int dmax = sum;
        for (int i = n - 3; i >= 0; i--) {
            sum -= stones[i + 2];
            dmax = Math.max(dmax, sum - dmax);
        }
        return dmax;
    }
}