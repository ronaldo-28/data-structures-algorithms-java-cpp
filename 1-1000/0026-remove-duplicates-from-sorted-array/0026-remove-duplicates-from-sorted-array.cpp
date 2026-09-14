class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        if(nums.size() == 0 || nums.size() == 1){
            return nums.size();
        }

        int currentIndex = 0;
        int swapIndex = 1;

        while(swapIndex < nums.size()){
            if(nums[currentIndex] < nums[swapIndex]){
                nums[++currentIndex] = nums[swapIndex++];
            }else{
                swapIndex++;
            }
        }
        return currentIndex + 1;
    }
};