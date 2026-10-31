class Solution {
public:
    int subarraySum(vector<int>& n, int s = 0) {
        for(int i=0;i<n.size();i++)
        s += accumulate(n.begin()+max(0, i-n[i]), n.begin()+i+1, 0);
        return s;
    }
};