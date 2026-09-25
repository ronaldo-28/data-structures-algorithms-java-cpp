// https://www.youtube.com/@0x3f
class Solution {
    public int maxValidSplits(int[] nums) {
        int n = nums.length;
        int[] preGcd = new int[n];
        int g = 0;
        for (int i = 0; i < n; i++) {
            g = gcd(g, nums[i]);
            preGcd[i] = g;
        }

        int[] sufGcd = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            sufGcd[i] = gcd(sufGcd[i + 1], nums[i]);
        }

        // 不删任何数
        int allGcd = sufGcd[0];
        int p = 0;
        while (preGcd[p] != allGcd) {
            p++;
        }
        int q = n - 1;
        while (sufGcd[q] != allGcd) {
            q--;
        }
        int ans = Math.max(q - p, 0); // 满足 i >= p 且 i+1 <= q 的 i 的个数

        for (int i = 0; i < n; i++) {
            if (i > 0 && preGcd[i] == preGcd[i - 1]) {
                continue;
            }

            // 删除 nums[i]
            int newG = i > 0 ? gcd(preGcd[i - 1], sufGcd[i + 1]) : sufGcd[i + 1];
            if (newG == allGcd) {
                continue;
            }

            g = 0;
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                g = gcd(g, nums[j]);
                if (g == newG) {
                    p = j;
                    break;
                }
            }

            g = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (j == i) {
                    continue;
                }
                g = gcd(g, nums[j]);
                if (g == newG) {
                    q = j;
                    break;
                }
            }

            int res = q - p;
            if (p <= i && i < q) {
                res--; // 因为删除了 nums[i]，少一个有效分割
            }
            ans = Math.max(ans, res);
            break;
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}