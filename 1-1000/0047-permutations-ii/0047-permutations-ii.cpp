class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {

    
        vector<vector<int>> Ans;
        sort(nums.begin(),nums.end());
        Ans.push_back(nums);
        while(next_permutation(nums.begin(),nums.end()))
          Ans.push_back(nums);
        

        return Ans;
    }
};