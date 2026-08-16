class Solution {
    public long getSum(int[] nums) {
        int n = nums.length;
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        long ans = 0;

        int[] odd = new int[n];
        int left = 0;
        int right = -1;

        for (int i = 0; i < n; i++) {
            int k = i > right ? 1 : Math.min(odd[left + right - i], right - i + 1);

            while (i - k >= 0 && i + k < n && nums[i - k] == nums[i + k]) {
                k++;
            }

            odd[i] = k;

            int l = i - k + 1;
            int r = i + k - 1;
            ans = Math.max(ans, prefix[r + 1] - prefix[l]);

            if (r > right) {
                left = l;
                right = r;
            }
        }

        int[] even = new int[n];
        left = 0;
        right = -1;

        for (int i = 0; i < n; i++) {
            int k = i > right ? 0 : Math.min(even[left + right - i + 1], right - i + 1);

            while (i - k - 1 >= 0 && i + k < n && nums[i - k - 1] == nums[i + k]) {
                k++;
            }

            even[i] = k;

            if (k > 0) {
                int l = i - k;
                int r = i + k - 1;
                ans = Math.max(ans, prefix[r + 1] - prefix[l]);

                if (r > right) {
                    left = l;
                    right = r;
                }
            }
        }

        return ans;
    }
}