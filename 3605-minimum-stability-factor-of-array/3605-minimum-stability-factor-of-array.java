class Solution {
    public int minStable(int[] nums, int maxC) {
        int n = nums.length;
        int[] left = new int[n];
        int[] vals = new int[40];
        int[] starts = new int[40];
        int size = 0;
        for (int r = 0; r < n; r++) {
            int m = 0;
            for (int i = 0; i < size; i++) {
                int g = gcd(vals[i], nums[r]);
                if (m > 0 && vals[m - 1] == g) continue;
                vals[m] = g;
                starts[m] = starts[i];
                m++;
            }
            if (m == 0 || vals[m - 1] != nums[r]) {
                vals[m] = nums[r];
                starts[m] = r;
                m++;
            }
            size = m;
            left[r] = r + 1;
            for (int i = 0; i < size; i++) {
                if (vals[i] >= 2) {
                    left[r] = starts[i];
                    break;
                }
            }
        }
        int lo = 0;
        int hi = n;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (canLimit(left, mid, maxC)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    boolean canLimit(int[] left, int len, int maxC) {
        int used = 0;
        int last = -1;
        for (int r = len; r < left.length; r++) {
            int l = r - len;
            if (last >= l) continue;
            if (left[r] <= l) {
                used++;
                last = r;
                if (used > maxC) return false;
            }
        }
        return true;
    }

    int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}