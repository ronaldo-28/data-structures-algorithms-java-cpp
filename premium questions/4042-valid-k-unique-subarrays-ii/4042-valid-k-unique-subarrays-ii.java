class Solution {
    private static final Random rand = new Random();
    private static final long[] mask = new long[500001];
    private static final int[] freq1 = new int[500001], freq2 = new int[500001];
    public boolean[] validSubarrays(int[] nums, int k, int l, int r, int q) {
        int n = nums.length;
        long[] prefix = new long[n + 1];

        int min = 0, max = 0;
        int[] leftMin = new int[n], leftMax = new int[n];
        int count1 = 0, count2 = 0;

        for(int i = 0; i < n; i++) {
            int num = nums[i];
            if(mask[num] == 0) mask[num] = rand.nextLong();
            prefix[i + 1] = prefix[i] ^ mask[num];

            if(freq1[num]++ == 0) count1++;
            if(freq2[num]++ == 0) count2++;

            if(count1 == k + 1) {
                while(--freq1[nums[min++]] != 0);
                count1--;
            }
            if(count2 == k) {
                while(--freq2[nums[max++]] != 0);
                count2--;
            }
            leftMin[i] = min;
            leftMax[i] = max;
        }
        while(min < n) freq1[nums[min++]]--;
        while(max < n) freq2[nums[max++]]--;

        boolean[] ans = new boolean[q];
        ans[0] = prefix[r + 1] == prefix[l] && l >= leftMin[r] && l < leftMax[r];
        for(int i = 1; i < q; i++) {
            int g = ans[i - 1] ? l + r : r - l;
            l = (l ^ g) % n;
            r = (r ^ g) % n;
            if(l > r) {
                int temp = l;
                l = r;
                r = temp;
            }

            ans[i] = prefix[r + 1] == prefix[l] && l >= leftMin[r] && l < leftMax[r];
        }

        return ans;
    }
}