class Solution {
    long[] temp;
    public int countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length;
        long[] p = new long[n + 1];
        temp = new long[n + 1];
        
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] + (nums[i] % 2 == 0 ? b : -a);
        }
        
        return (int) find(p, 0, n);
    }
    
    private long find(long[] p, int l, int r) {
        if (l >= r) return 0;
        
        int m = l + (r - l) / 2;
        long count = find(p, l, m) + find(p, m + 1, r);
        
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if (p[i] >= p[j]) {
                count += (m - i + 1);
                temp[k++] = p[j++];
            } else {
                temp[k++] = p[i++];
            }
        }
        
        while (i <= m) temp[k++] = p[i++];
        while (j <= r) temp[k++] = p[j++];
        for (i = l; i <= r; i++) p[i] = temp[i];
        
        return count;
    }
}