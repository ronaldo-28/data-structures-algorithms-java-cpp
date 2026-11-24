class Solution {
public:
    static constexpr int masks[] = {
        0,     
        0,     
        1 << 2,     
        1 << 3,     
        1 << 2,     
        1 << 5,     
        (1 << 3) | (1 << 2),     
        1 << 7,     
        1 << 2,     
        1 << 3,     
        (1 << 2) | (1 << 5)      
    };
    bool checkDivs(int divs_mask, int num){
        return divs_mask & masks[num];
    }
    void addDivs(int& divs_mask, int num){
        divs_mask = divs_mask | masks[num];
    }
    void removeDivs(int& divs_mask, int num){
        divs_mask = divs_mask & (~masks[num]);
    }
    int maxLength(vector<int>& nums) {
        int left = 0;
        int right = 1;
        int divs_mask = 0;
        addDivs(divs_mask, nums[0]);

        int n = nums.size();
        int max_len = 2;
        while(right < n){
            while(checkDivs(divs_mask, nums[right]) && left < right){
                removeDivs(divs_mask, nums[left]);
                ++left;
            }
            addDivs(divs_mask, nums[right]);
            max_len = std::max(max_len, right - left + 1);
            ++right;
        }
        return max_len;
    }
};