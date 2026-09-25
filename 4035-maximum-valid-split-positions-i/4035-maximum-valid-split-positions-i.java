class Solution {
    public int maxValidSplits(int[] nums) {
        int n = nums.length;
        int[] pref = new int[n + 1], suf = new int[n + 1];
        for (int k = 0; k < n; k++) pref[k + 1] = gcd(pref[k], nums[k]);  // gcd(nums[:k])
        for (int k = n - 1; k >= 0; k--) suf[k] = gcd(suf[k + 1], nums[k]);  // gcd(nums[k:])

        int total = pref[n], best = 0;
        for (int k = 0; k <= n; k++) if (pref[k] == suf[k]) best++;  // remove nothing

        for (int i = 0; i < n; i++) {
            int g = gcd(pref[i], suf[i + 1]);  // gcd once i is gone
            if (g == total) continue;  // removing i changes nothing
            int l = pref[i] == g ? walk(nums, 0, 0, 1, g) : walk(nums, i + 1, pref[i], 1, g);
            int r = suf[i + 1] == g ? walk(nums, n - 1, 0, -1, g) : walk(nums, i - 1, suf[i + 1], -1, g);
            best = Math.max(best, r - l + 2 - (l <= i && i <= r ? 1 : 0));
        }

        return best;
    }
    private static int walk(int[] nums, int k, int cur, int step, int g) {   // extend until it reaches g
        while (cur != g) { 
            cur = gcd(cur, nums[k]); 
            k += step; 
        }
        return k;
    }
    static int gcd(int a, int b) { 
        while (b != 0) { 
            int t = a % b; 
            a = b; 
            b = t; 
        } 
        return a; 
    }
}