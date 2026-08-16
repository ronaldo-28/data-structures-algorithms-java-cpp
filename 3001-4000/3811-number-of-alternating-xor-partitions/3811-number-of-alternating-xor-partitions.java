import java.util.*;

class Solution {
    private static final int M = 1000000007;

    private int add(int x, int y) {
        x += y;
        if (x >= M) x -= M;
        return x;
    }

    public int alternatingXOR(int[] nums, int target1, int target2) {
        Map<Integer, Integer> dp1 = new HashMap<>();
        Map<Integer, Integer> dp2 = new HashMap<>();
        dp2.put(0, 1);

        int s = 0;
        int x1 = 0, x2 = 0;

        for (int x : nums) {
            s ^= x;
            x1 = dp2.getOrDefault(s ^ target1, 0);
            x2 = dp1.getOrDefault(s ^ target2, 0);

            dp1.put(s, add(dp1.getOrDefault(s, 0), x1));
            dp2.put(s, add(dp2.getOrDefault(s, 0), x2));
        }

        int r = x1;
        r = add(r, x2);
        return r;
    }
}