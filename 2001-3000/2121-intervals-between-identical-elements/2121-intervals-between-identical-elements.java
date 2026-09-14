class Solution {
    public long[] getDistances(int[] arr) {
        int n = arr.length;
        int max = 0;
        for(int x : arr) max = Math.max(x, max);
        long[] freq = new long[max + 1];
        long[] indices = new long[max + 1];
        long[] ans = new long[n];

        for(int i = 0; i < n; i ++){
            int x = arr[i];
            ans[i] += i * freq[x]++ - indices[x];
            indices[x] += i;
        }
        for(int i = 0; i < n; i ++){
            int x = arr[i];
            ans[i] -= i * freq[x]-- - indices[x];
            indices[x] -= i;
        }
        return ans;
    }
}