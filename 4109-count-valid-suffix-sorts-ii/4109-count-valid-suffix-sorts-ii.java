class Solution {
    public int[] countSuffix(int[] nums, int[] target) {
        int n = nums.length, m = target.length;
        int[] ans = new int[m - n + 1];
        int[] com = new int[n + 1 + m];
        System.arraycopy(nums, 0, com, 0, n);
        System.arraycopy(target, 0, com, n + 1, m);
        int lCom = com.length;
        int[] z = new int[lCom];
        int L = 0, R = 0;
        for (int i = 1; i < lCom; i++) {
            if (i <= R) z[i] = Math.min(R - i + 1, z[i - L]);
            while (i + z[i] < lCom && com[z[i]] == com[i + z[i]]) z[i]++;
            if (i + z[i] - 1 > R) { L = i; R = i + z[i] - 1; }
        }
        int[] pre = new int[m];
        for (int i = 1; i < m; i++)
            pre[i] = target[i - 1] >= target[i] ? pre[i - 1] : i;

        int[] freq = new int[100001];
        int bad = 0;
        for (int x : nums) {
            int fx = freq[x];
            if (fx > 0 && fx == 1) bad--;
            freq[x] = fx - 1;
        }
        for (int i = 0; i < n; i++) {
            int x = target[i], fx = freq[x];
            if (fx == 0) bad++;
            freq[x] = fx + 1;
        }
        int zs = n + 1;
        for (int k = 0; k <= m - n; k++) {
            if (bad == 0) {
                int minI = Math.max(0, pre[k + n - 1] - k);
                int maxI = Math.min(n - 1, z[zs + k]);
                if (maxI >= minI) ans[k] = maxI - minI + 1;
            }
            if (k + n < m) {
                int x1 = target[k], fx1 = freq[x1];
                if (fx1 > 0 && fx1 == 1) bad--;
                freq[x1] = fx1 - 1;
                int x2 = target[k + n], fx2 = freq[x2];
                if (fx2 == 0) bad++;
                freq[x2] = fx2 + 1;
            }
        }
        return ans;
    }
}