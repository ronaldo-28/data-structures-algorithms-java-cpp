class Solution {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int n = arr1.length, m = 0;
        for(int i = 1; i < arr2.length; i++) {
            if(arr2[i] != arr2[m]) arr2[++m] = arr2[i];
        }
        m++;

        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            int lo = binarySearch(arr2, m, arr1[i]);
            int val = lo < i ? Integer.MIN_VALUE : 0;
            if(i > 0 && arr1[i - 1] < arr1[i]) val = Math.max(val, dp[i - 1]);
            for(int j = 1; j <= lo && j < i; j++) {
                if(arr1[i - j - 1] < arr2[lo - j]) val = Math.max(val, dp[i - j - 1]); 
            }
            dp[i] = val + 1;
        }
        int val = m < n ? Integer.MIN_VALUE : 0;
        val = Math.max(val, dp[n - 1]);
        for(int j = 1; j <= m && j < n; j++) {
            if(arr1[n - j - 1] < arr2[m - j]) val = Math.max(val, dp[n - j - 1]); 
        }
        return val < 0 ? -1 : n - val;
    }
    private int binarySearch(int[] arr, int right, int target) {
        int left = 0;
        while(left < right) {
            int mid = left + right >>> 1;
            if(arr[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}