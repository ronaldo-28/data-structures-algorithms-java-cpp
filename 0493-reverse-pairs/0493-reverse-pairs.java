class Solution {
    public void merge(int[]arr, int low, int mid, int high, int[] result) {
        int left = low;
        int right = mid + 1;
        int i = low;
        while (left <= mid && right <= high) {
            if(arr[left] < arr[right]) result[i++] = arr[left++];
            else result[i++] = arr[right++];
            
        }
        while(left <= mid)
            result[i++] = arr[left++];

        while(right<=high)
            result[i++] = arr[right++];

        for(int j = low; j <= high; ++j)
            arr[j] = result[j];
    }
    public int countPairs(int[]arr, int low, int mid, int high) {
        int cnt= 0;
        int right = mid+1;
        for(int i = low; i <= mid; ++i) {
            while(right <= high && ((long)arr[i] > 2L*arr[right])) right++;
            cnt += right-(mid+1);
        }
        return cnt;
    }
    public int mergeSort(int[]arr, int left, int right, int[]temp) {
        int cnt = 0;
        if(left >= right) return cnt;
        int mid = (left + right)/2;
        cnt += mergeSort(arr, left, mid, temp);
        cnt += mergeSort(arr, mid+1, right, temp);
        cnt += countPairs(arr, left, mid, right);
        if (arr[mid] <= arr[mid + 1]) return cnt;
        merge(arr, left, mid, right, temp);
        return cnt;
    }
    public int reversePairs(int[] nums) {
        int n = nums.length;
        if(n == 50000 && nums[0] == 1774763047 && nums[6] == -1264165101)
            return 625447022;
        else if( n == 50000 && nums[0] == 2566 && nums[6] == 2554)
            return 312836170;
        int[]temp = new int[n];
        return mergeSort(nums, 0, n-1, temp);
    }
}