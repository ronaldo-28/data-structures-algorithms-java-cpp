class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 2; // Peak cannot be the last element

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1; // Peak is in the right part
            } else {
                right = mid - 1; // Peak is in the left part or at mid
            }
        }
        return left; // left will point to the peak index
    }
}