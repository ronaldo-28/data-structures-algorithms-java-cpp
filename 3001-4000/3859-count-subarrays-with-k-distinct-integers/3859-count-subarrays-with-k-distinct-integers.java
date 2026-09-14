class Solution {
    private static final int[] freq = new int[100001];
    public long countSubarrays(int[] nums, int k, int m) {
        long ans = 0;
        int count1 = 0, count2 = 0, n = nums.length, left = 0, validCount = 0;

        for(int i = 0; i < n; i++) freq[nums[i]] = 0;

        for(int right = 0; right < n; right++) {
            if(freq[nums[right]]++ == 0) count1++;
            if(freq[nums[right]] == m) count2++;
            while(count1 > k) {
                if(freq[nums[left]]-- == m) count2--;
                if(freq[nums[left++]] == 0) count1--;
                validCount = 0;
            }
            while(count1 == k && count2 == k) {
                if(freq[nums[left]] <= m) break;
                freq[nums[left++]]--;
                validCount++;
            }
            if(count1 == k && count2 == k) ans += 1 + validCount;
        }
        return ans;
    }
}