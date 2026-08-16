class Solution {
public:
    int firstUniqueEven(vector<int>& nums) {
        std::unordered_map<int, int> counts;
       for(int num:nums){
           if (num % 2 == 0) {
            counts[num]++;
        }
       } 

        for (int num : nums) {
        if (num % 2 == 0 && counts[num] == 1) {
            return num;
        }
    }

    return -1;
    }
};