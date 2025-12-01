class Solution {
public:
    int findContentChildren(vector<int>& g, vector<int>& s) {
        sort(g.begin(),g.end());
        sort(s.begin(),s.end());
        int i_g=0;
        int i_s=0;
        while(i_g<g.size() && i_s<s.size()){
            if(g[i_g] <= s[i_s]){
                i_g++;
            }
            i_s++;
        }
        return i_g;
    }
};