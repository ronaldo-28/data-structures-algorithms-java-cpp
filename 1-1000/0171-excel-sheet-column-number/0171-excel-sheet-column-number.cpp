class Solution {
public:
    int titleToNumber(string s) {
        int res=0;
        int end = s.size()-1;
        for (int i=0; i<s.size();i++)
            res+=(pow(26, i))*(s[end-i]-'A'+1);
        
        return res;
    }
};