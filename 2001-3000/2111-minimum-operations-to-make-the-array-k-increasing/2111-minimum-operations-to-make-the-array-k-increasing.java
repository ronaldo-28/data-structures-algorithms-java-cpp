class Solution {
    public int kIncreasing(int[] arr, int k) {
        int n = arr.length;
        int ans = 0;
        int[] lis = new int[n / k + 1];
        for(int i = 0; i < k; ++i) {
            int max = 0;
            lis[0] = arr[i];
            for(int j = i + k; j < n; j += k) lis[arr[j] >= lis[max] ? ++max : binarySearch(lis, arr[j], max)] = arr[j];
            ans += max + 1;
        }
        return n - ans;
    }
    private static int binarySearch(int[] arr, int target, int right) {
        int left = 0;
        while(left < right) {
            int mid = (left + right) >>> 1;
            if(arr[mid] > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}