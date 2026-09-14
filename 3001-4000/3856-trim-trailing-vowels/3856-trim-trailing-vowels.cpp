class Solution {
public:
    string trimTrailingVowels(string s) {
        set<char> st={'a','e','i','o','u'};
        int i=s.size()-1;
        for(;i>=0;--i){
            if(!st.count(s[i])) break;
        }
        return s.substr(0,i+1);
    }
};