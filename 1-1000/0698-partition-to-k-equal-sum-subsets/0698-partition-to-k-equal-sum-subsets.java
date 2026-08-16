class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        if(sum % k != 0) return false;

        int target = sum / k;        
        // Arrays.sort(nums);

        int n = nums.length;
        if(nums[n - 1] > target) return false;
        boolean[] visited = new boolean[n];

        return backtrack(nums, visited, 0, k, 0, target);
    }

    private boolean backtrack(int[] nums, boolean[] visited, int start, int k, int currentSum, int target){
        if(k == 1) return true;

        if(currentSum == target) return backtrack(nums, visited, 0, k - 1, 0, target);

        for(int i = start; i < nums.length; i++){
            if(!visited[i] && currentSum + nums[i] <= target){
                visited[i] = true;

                if(backtrack(nums, visited, i + 1, k, currentSum + nums[i], target)) return true;

                visited[i] = false;

                if(currentSum == 0) return false;
            }
        }

        return false;
    }
}