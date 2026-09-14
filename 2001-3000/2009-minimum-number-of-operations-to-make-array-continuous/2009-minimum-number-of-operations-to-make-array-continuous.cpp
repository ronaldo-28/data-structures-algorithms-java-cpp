#pragma GCC optimize("O2")
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size();
        static int tmp[100001];
        int* a = nums.data();
        // 8-bit radix sort (4 passes, cnt[256] stays in L1 cache)
        for (int shift = 0; shift < 32; shift += 8) {
            int cnt[256] = {};
            for (int i = 0; i < n; i++) cnt[(a[i]>>shift)&255]++;
            for (int j = 1; j < 256; j++) cnt[j] += cnt[j-1];
            for (int j = n-1; j >= 0; j--) tmp[--cnt[(a[j]>>shift)&255]] = a[j];
            memcpy(a, tmp, n*4);
        }
        // Deduplicate in-place
        int m = 1;
        for (int i = 1; i < n; i++) if (a[i] != a[i-1]) a[m++] = a[i];
        // Sliding window
        int ans = n, j = 0, lim = n - 1;
        for (int i = 0; i < m; i++) {
            int hi = a[i] + lim;
            while (j < m && a[j] <= hi) j++;
            if (n - (j - i) < ans) ans = n - (j - i);
        }
        return ans;
    }
};