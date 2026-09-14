class Solution {
    public int countSubarrays(int[] nums, int k) {
        int len = nums.length;
        int idx = 0;

        while (nums[idx] != k)
            ++idx;

        int[] freq = new int[len * 2 + 1];
        int base = len;
        int sum = 0;
        freq[base] = 1;

        for (int i = idx - 1; i >= 0; --i) {
            if (nums[i] > k)
                ++sum;
            else
                --sum;

            ++freq[base + sum];
        }

        int ans = 0;
        sum = 0;

        for (int i = idx; i < len; ++i) {
            if (nums[i] > k)
                ++sum;
            else if (nums[i] < k)
                --sum;

            ans += freq[base - sum];
            ans += freq[base - sum + 1];
        }

        return ans;
    }
}