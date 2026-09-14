class Solution {
public:
    vector<int> findIndices(vector<int>& nums, int indexDifference, int valueDifference) {
        int n = nums.size();
        int minIdx = 0, maxIdx = 0;  // track best candidates
        
        for (int j = indexDifference; j < n; j++) {
            int i = j - indexDifference;
            
            // update best min and max index seen so far
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;

            // check against min and max
            if (abs(nums[j] - nums[minIdx]) >= valueDifference) return {minIdx, j};
            if (abs(nums[j] - nums[maxIdx]) >= valueDifference) return {maxIdx, j};
        }
        
        return {-1, -1};
    }
};