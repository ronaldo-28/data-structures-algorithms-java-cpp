class Solution {
    public int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        int rightBoundary = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int missingCount = arr[mid] - (mid + 1);
            if (missingCount < k) {
                rightBoundary = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (rightBoundary == -1) {
            return k;
        } else {
            int missingBeforeRight = arr[rightBoundary] - (rightBoundary + 1);
            return arr[rightBoundary] + (k - missingBeforeRight);
        }
    }
}