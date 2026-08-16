class Solution {
    public int[] kthRemainingInteger(int[] nums, int[][] queries) {
        int q = queries.length, n = nums.length;

        int[] e = new int[n];
        e[0] = (nums[0] % 2 == 0) ? 1 : 0;

        for (int i = 1; i < n; i++) {
            e[i] = e[i - 1] + (nums[i] % 2 == 0 ? 1 : 0);
        }

        int[] ans = new int[q];
        int idx = 0;
        for (int[] x : queries) {
            int l = x[0], r = x[1], k = x[2];
            int s = 2, end = Integer.MAX_VALUE-2;
            int z = -1;
            while (s <= end) {
                int mid = s + (end - s) / 2;
                if ((mid & 1) == 1) mid--;
                if (helper(l, r, nums, mid, e) >= k) {
                    z = mid;
                    end = mid - 2;
                } else {
                    s = mid + 2;
                }
            }
            ans[idx++] = z;
        }

        return ans;
    }

    public int helper(int l, int r, int[] nums, int mid, int[] e) {
        int min = nums[l], max = nums[r];

        if (min > mid) {
            return mid / 2;
        } 
        else if (max < mid) {
            int cnt = e[r];
            if (l > 0) cnt -= e[l - 1];
            return mid / 2 - cnt;
        } 
        else {
            int s = l, end = r;
            int i = -1;

            while (s <= end) {
                int y = s + (end - s) / 2;

                if (nums[y] <= mid) {
                    i = y;
                    s = y + 1;
                } else {
                    end = y - 1;
                }
            }

            int cnt = e[i];
            if (l > 0) cnt -= e[l - 1];

            return mid / 2 - cnt;
        }
    }
}