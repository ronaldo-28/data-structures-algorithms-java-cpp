class Solution {
    public long maxSum(int[][] grid, int[] limits, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[] list = new int[m * n];
        int size = 0;
        long sum = 0;
        for(int i = 0; i < m; i++){
            int limit = Math.min(limits[i], n);
            if(limit == 0) continue;
            quickSelect(grid[i], 0, n - 1, n - limit);
            for(int j = n - limit; j < n; j++){
                list[size++] = grid[i][j];
            }
        }

        quickSelect(list, 0, size - 1, size - k);
        for(int i = size - k; i < size; i++){
            sum += list[i];
        }
        return sum;
    }
    private void quickSelect(int[] nums, int left, int right, int k) {
        while (left < right) {
            int pivotIndex = partition(nums, left, right);

            if (pivotIndex == k) {
                return;
            } else if (pivotIndex < k) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }

        int temp = nums[i];
        nums[i] = nums[right];
        nums[right] = temp;

        return i;
    }
}