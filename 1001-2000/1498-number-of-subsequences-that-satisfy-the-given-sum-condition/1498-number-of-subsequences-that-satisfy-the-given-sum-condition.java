class Solution {
    public int numSubseq(int[] nums, int target) {
        int[] freq = new int[target], tpsum = new int[nums.length];
        int mod = (int)1e9 + 7, ans = 0;
        for (int n: nums) {
            if (n < target) freq[n]++;
        }
        long prev = 1;
        tpsum[0] = 1;
        for (int i = 1; i < target; i++) freq[i] += freq[i - 1];
        for (int i = 1; i < nums.length; i++){
            long cur = (prev << 1) % mod;
            tpsum[i] = (int)((cur + tpsum[i - 1]) % mod);
            prev = cur;
        }
        for (int i = 1; i <= (target >> 1); i++) {
            if (freq[i] == freq[i - 1]) continue;
            int maxRight = target - i;
            int right = freq[maxRight] - freq[i - 1] - 1;
            int left = right - (freq[i] - freq[i - 1]) + 1;
            long csum = tpsum[right];
            if (left != 0) csum = (csum - tpsum[left - 1] + mod) % mod;
            ans = (int)((ans + csum) % mod);
        }
        return ans;
    }
}