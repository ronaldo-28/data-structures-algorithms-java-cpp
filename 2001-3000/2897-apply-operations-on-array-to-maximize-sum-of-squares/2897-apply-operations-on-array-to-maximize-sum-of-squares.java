class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] cnt = new int[32];
        int mod = (int) (1e9 + 7);
        for (int num : nums)
        {
            for (int i = 0; i < 32; ++i)
            {
                cnt[i] += num >> i & 1;
            }
        }
        long ans = 0;
        for (int i = 0; i < k; ++i)
        {
            int c = 0;
            for (int j = 0; j < 32; ++j)
            {
                if (cnt[j] == 0) continue;
                c |= 1 << j;
                --cnt[j];
            }
            ans = (ans + 1L * c * c) % mod;
        }
        return (int) ans;
    }
}