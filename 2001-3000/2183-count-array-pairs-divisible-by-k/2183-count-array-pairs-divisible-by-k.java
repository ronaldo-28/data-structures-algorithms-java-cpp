class Solution {
    public long countPairs(int[] nums, int k) {
        int[] freq = new int[k + 1];
        int count = 0;
        for(int num : nums) {
            if(freq[gcd(num, k)]++ == 0) count++;
        }
        long[] arr1 = new long[count];
        long[] arr2 = new long[count];
        int idx = 0;
        for(int i = 0; i <= k; i++) {
            if(freq[i] > 0) {
                arr1[idx] = i;
                arr2[idx++] = freq[i];
            }
        }
        long ans = 0;
        for(int i = 0; i < count; i++) {
            if(arr1[i] * arr1[i] % k == 0) ans += arr2[i] * (arr2[i] - 1) / 2;
            for(int j = i + 1; j < count; j++) {
                if(arr1[i] * arr1[j] % k == 0) ans += arr2[i] * arr2[j];
            }
        }
        return ans;
    }
    private static int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }
}