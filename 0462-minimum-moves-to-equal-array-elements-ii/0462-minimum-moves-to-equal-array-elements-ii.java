class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        int m = n >>> 1;
        quickselect(nums, 0, n-1, m);
        int median = nums[m];
        
        int result = 0;
        for(int i=0; i<m; ++i) {
            result += median - nums[i];
        }
        for(int i=m; i<n; ++i) {
            result += nums[i] - median;
        }

        return result;
    }


    private static void quickselect(int[] nums, int left, int right, int n) {
        if(left >= right) return;

        int part = partition(nums, left - 1, right + 1);
        if(part >= n) {
            quickselect(nums, left, part, n);
        } else {
            quickselect(nums, part + 1, right, n);        
        }
    }


    private static int partition(int[] nums, int left, int right) {
        int pivot = choose(nums[left + 1], 
                           nums[left + right >>> 1], 
                           nums[right - 1]);
        
        while(true) {
            do {
                left++;
            } while(nums[left] < pivot);

            do {
                right--;
            } while(nums[right] > pivot);
            
            if(left >= right) return right;

            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
        }
    }


    private static int choose(int a, int b, int c) {
        if((a >= b) ^ (a >= c)) {
            return a;
        }
        if((a >= b) ^ (c >= b)) {
            return b;
        }
        return c;
    }
}