class Solution {
    public int totalSteps(int[] nums) {
        if (nums.length == 1 || (nums.length == 2 && nums[0] <= nums[1])) return 0;
        if (nums.length == 2 && nums[0] > nums[1]) return 1;

        int len = nums.length;
        int[] stepsToRemove = new int[len];
        
        // optimization: raw array instead of stack
        int[] indices = new int[len];
        int idx = -1; // pointer to keep track of index of last inserted value

        int maxSteps = 0;

        // iterate from right to left to handle cascading removals
        for (int i = len - 1; i >= 0; --i) {
            // while current element is greater than element at end of indices (means it will remove
            // the latter)
            // update stepsToRemove[i] with the max of one more step* plus previously calculated removals
            // or number of removals to be completed by indices element (which current element will
            // eventually remove)
            //
            // *one more step is b/c it will remove the indices element (nums[indices[idx]])
            while (idx != -1 && nums[i] > nums[indices[idx]]) {
                stepsToRemove[i] = Math.max(stepsToRemove[i] + 1, stepsToRemove[indices[idx]]);
                maxSteps = Math.max(maxSteps, stepsToRemove[i]);

                --idx;
            }

            indices[++idx] = i;    
        }

        return maxSteps;
    }
}