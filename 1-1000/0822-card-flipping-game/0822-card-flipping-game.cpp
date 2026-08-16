class Solution {
public:
    int flipgame(vector<int>& fronts, vector<int>& backs) {
        unordered_set<int> bad;
        for(int i=0;i<fronts.size();i++) if(fronts[i]==backs[i]) bad.insert(fronts[i]);
        int ans=INT_MAX;
        for(int x: fronts) if(!bad.count(x)) ans=min(ans,x);
        for(int x: backs) if(!bad.count(x)) ans=min(ans,x);
        return ans==INT_MAX?0:ans;
    }
};