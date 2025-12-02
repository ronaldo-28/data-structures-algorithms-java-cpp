class Solution {
    private static final int MOD = 1000000007;
    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int[] point : points) freq.merge(point[1], 1, Integer::sum);

        long sum = 0, ans = 0;
        for(int x : freq.values()) {
            long val = x * (x - 1L) / 2L;
            ans += val * sum;
            sum += val;
        }
        return (int)(ans % MOD);
    }
}