class Solution {
    public long largestPerimeter(int[] nums) {
        int n = nums.length;
        for(int i=n-1;i>1;i--) {
            long ans = findSolution(nums, i);
            if(ans != -1) return ans; // found answer
        }
        return -1;
    }

    public long findSolution(int[] nums, int end) {
        int maxIndex = 0;
        for(int i=0;i<=end;i++) {
            if(nums[maxIndex] < nums[i]) maxIndex = i;
        }
        long sum = 0;
        for(int i=0;i<=end;i++) {
            if(nums[i] <= nums[maxIndex]) sum += nums[i];
        }   

        // move maximum to end
        int temp = nums[maxIndex];
        nums[maxIndex] = nums[end];
        nums[end] = temp;

        if(sum > 2*temp) return sum;
        return -1;
    }
}