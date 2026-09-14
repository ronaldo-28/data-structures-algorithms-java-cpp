class Solution {
public:
    vector<int> findBuildings(vector<int>& heights) {
        vector<int> res;
        int curr_max = 0;
        
        for (int i = heights.size()-1; i >= 0; i--) {
            if (heights[i] > curr_max) {
                res.push_back(i);
                curr_max = heights[i];
            }
        }
        reverse(res.begin(), res.end());
        return res;
    }
};