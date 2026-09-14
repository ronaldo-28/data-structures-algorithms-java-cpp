import java.util.*;

class Solution {

    public int minChanges(int[] nums, int k) {
        int n = nums.length;

        int[][] bucket = new int[k][1024];
        int[] size = new int[k];

        for (int i = 0; i < n; i++) {
            int g = i % k;
            bucket[g][nums[i]]++;
            size[g]++;
        }

        int[] cur = new int[1024];
        Arrays.fill(cur, 1 << 29);
        cur[0] = 0;

        for (int g = 0; g < k; g++) {

            int base = Integer.MAX_VALUE;
            for (int v : cur) base = Math.min(base, v);

            int[] next = new int[1024];
            Arrays.fill(next, base + size[g]);

            for (int val = 0; val < 1024; val++) {
                if (bucket[g][val] == 0) continue;

                int keep = size[g] - bucket[g][val];

                for (int mask = 0; mask < 1024; mask++) {
                    next[mask ^ val] =
                        Math.min(next[mask ^ val], cur[mask] + keep);
                }
            }

            cur = next;
        }

        return cur[0];
    }
}