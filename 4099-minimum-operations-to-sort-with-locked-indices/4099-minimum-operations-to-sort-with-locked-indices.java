class Solution {
    public int minOperations(int[] nums, int[] locked, int k) {
        int n = nums.length;

        int[] diff = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int target = nums[i] - 1;

            if (i == target)
                continue;

            int l = Math.min(i, target);
            int r = Math.max(i, target);

            diff[l]++;
            diff[r + 1]--;
        }

        boolean[] need = new boolean[n];
        int cur = 0;
        int unlocks = 0;

        for (int i = 0; i < n; i++) {
            cur += diff[i];
            need[i] = cur > 0;

            if (need[i] && locked[i] == 1)
                unlocks++;
        }

        if (unlocks > k)
            return -1;

        long ans = unlocks;
        int[] bit = new int[n + 1];

        for (int l = 0; l < n;) {
            if (!need[l]) {
                l++;
                continue;
            }

            int r = l;
            while (r + 1 < n && need[r + 1])
                r++;

            for (int i = l; i <= r; i++) {
                int x = nums[i];

                int prefix = 0;
                for (int j = x; j > 0; j -= j & -j)
                    prefix += bit[j];

                ans += (i - l) - prefix;

                for (int j = x; j <= n; j += j & -j)
                    bit[j]++;
            }

            for (int i = l; i <= r; i++) {
                int x = nums[i];

                for (int j = x; j <= n; j += j & -j)
                    bit[j]--;
            }

            l = r + 1;
        }

        return (int) ans;
    }
}