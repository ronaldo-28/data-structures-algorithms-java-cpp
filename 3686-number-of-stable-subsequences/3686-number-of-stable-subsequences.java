class Solution {
    private static final int MOD = 1_000_000_007;

    public int countStableSubsequences(int[] nums) {
        long even1 = 0, even2 = 0;
        long odd1 = 0, odd2 = 0;

        for (int x : nums) {
            if ((x & 1) == 0) {
                long nextEven1 = (even1 + odd1 + odd2 + 1) % MOD;
                long nextEven2 = (even2 + even1) % MOD;
                even1 = nextEven1;
                even2 = nextEven2;
            } else {
                long nextOdd1 = (odd1 + even1 + even2 + 1) % MOD;
                long nextOdd2 = (odd2 + odd1) % MOD;
                odd1 = nextOdd1;
                odd2 = nextOdd2;
            }
        }

        long total = (even1 + even2) % MOD;
        total = (total + odd1) % MOD;
        total = (total + odd2) % MOD;
        return (int) total;
    }
}