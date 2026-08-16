class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        int steps = 0;

        int median = findMedian(nums, 0, nums.length-1, nums.length/2);
        
        for(int i=0; i<n; i++) steps += Math.abs(nums[i] - median);
        return steps;
    }

    public int findMedian(int[] nums, int l, int r, int k) {
        
        if (l >= r) return nums[l];
        int p = nums[l+new Random().nextInt(r-l+1)];

        int i = l;
        int j = r;
        while (i <= j) {
            while (nums[i] < p) i++;
            while (nums[j] > p) j--;

            if (i <= j) {
                int t = nums[j];
                nums[j--] = nums[i];
                nums[i++] = t;
            }
        }

        if (k <= j) return findMedian(nums, l, j, k);
        if (k >= i) return findMedian(nums, i, r, k);
        return nums[k];
    }
}