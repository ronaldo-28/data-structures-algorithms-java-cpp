class Solution {
public:
    int findClosestNumber(vector<int>& nums) {
        int closest = nums[0];
        int minDist = abs(nums[0]);

        for (int num : nums) {
            int dist = abs(num);

            if (dist < minDist || (dist == minDist && num > closest)) {
                closest = num;
                minDist = dist;
            }
        }

        return closest;
    }
};