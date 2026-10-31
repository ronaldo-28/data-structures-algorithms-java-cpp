class Solution {
    public long minCost(int[] arr, int[] brr, long k) {
        long res = calc(arr, brr);
        if (res <= k) return res;
        Arrays.sort(arr);
        Arrays.sort(brr);
        return Math.min(res, k + calc(arr, brr));
    }
    
    long calc(int[] a1, int[] a2) {
        int n = a1.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.abs(a1[i] - a2[i]);
        }
        return res;
    }
}