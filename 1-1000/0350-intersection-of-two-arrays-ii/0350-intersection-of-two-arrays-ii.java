class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] freq = new int[1001];
        for (int n : nums1) freq[n]++;

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int k = 0;
        for (int n : nums2) {
            if (freq[n] > 0) {
                res[k++] = n;
                freq[n]--;
            }
        }

        return Arrays.copyOfRange(res, 0, k);
    }
}