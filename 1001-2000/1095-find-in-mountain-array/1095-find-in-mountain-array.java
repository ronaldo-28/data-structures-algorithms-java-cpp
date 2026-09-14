class Solution {

    private MountainArray mountainArray;
    private int targetValue;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        this.mountainArray = mountainArr;
        this.targetValue = target;

        int n = mountainArray.length();
        // Find the peak of the mountain array
        int peakIndex = findPeakIndex(0, n - 1);
      
        // First, try to find the target in the ascending part
        int index = binarySearch(0, peakIndex, 1);
      
        // If the target is not found in the ascending part, search in the descending part
        if (index == -1) {
            index = binarySearch(peakIndex + 1, n - 1, -1);
        }
      
        return index;
    }

    // Use binary search to find the peak of the mountain
    private int findPeakIndex(int left, int right) {
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (mountainArray.get(mid) > mountainArray.get(mid + 1)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // Left and right converge to peak index
        return left;
    }

    // Modified binary search to handle ascending or descending order
    private int binarySearch(int left, int right, int direction) {
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int midValue = mountainArray.get(mid);
            if (midValue == targetValue) {
                return mid;
            }
          
            if ((midValue < targetValue) == (direction > 0)) {
                left = mid + 1; // Ascending and target is larger or descending and target is smaller
            } else {
                right = mid - 1;
            }
        }
        return -1; // Target is not found within the search space
    }
}
