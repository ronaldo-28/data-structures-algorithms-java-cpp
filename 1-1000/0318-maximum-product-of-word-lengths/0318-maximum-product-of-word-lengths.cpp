class Solution {
public:
    int maxProduct(vector<string>& words) {
        int n = words.size(), maxi = 0;
        vector<int> masks(n);
        for(int i = 0; i < n; ++i){
            int mask = 0;
            for(int j = 0, m = words[i].size(); j < m; ++j) mask |= (1 << (words[i][j] - 'a'));
            masks[i] = mask;
        }
        for(int i = 0; i < n; ++i){
            for(int j = i + 1; j < n; ++j){
                if(masks[i] & masks[j]) continue;
                int a = words[i].size(), b = words[j].size();
                maxi = max(maxi , a * b);
            }
        }
        return maxi;
    }
};