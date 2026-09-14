class Solution {
    public int fixedPoint(int[] arr) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (mid > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left == arr[left] ? left : -1;
    }
}