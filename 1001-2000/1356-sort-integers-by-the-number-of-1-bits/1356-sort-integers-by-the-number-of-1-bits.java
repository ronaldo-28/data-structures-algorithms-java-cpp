class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n; i++) arr[i] += 10001 * Integer.bitCount(arr[i]);
        quicksort(arr, 0, n - 1);
        for(int i = 0; i < n; i++) arr[i] %= 10001;
        return arr;
    }
    private static void quicksort(int[] nums, int left, int right) {
        if(left < right) {
            int part = partition(nums, left - 1, right + 1);
            quicksort(nums, left, part);
            quicksort(nums, part + 1, right);
        }
    }
    private static int partition(int[] nums, int left, int right) {
        int current = getPivot(nums[left + 1], nums[left + right >>> 1], nums[right - 1]), temp = 0;
        while(true) {
            do {
                left++;
            }while(nums[left] < current);
            do {
                right--;
            }while(nums[right] > current);
            
            if(left >= right) return right;

            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }
    private static int getPivot(int a, int b, int c) {
        if((a >= b) ^ (a >= c)) return a;
        if((a >= b) ^ (c >= b)) return b;
        return c;
    }
}