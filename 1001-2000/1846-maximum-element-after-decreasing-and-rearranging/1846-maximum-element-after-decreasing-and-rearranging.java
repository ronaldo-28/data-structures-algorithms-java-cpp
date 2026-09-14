class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] count = new int[n + 1];

        for (int num : arr) {
            if (num > n) count[n]++;
            else count[num]++;
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans + count[i], i);
        }

        return ans;
    }
}