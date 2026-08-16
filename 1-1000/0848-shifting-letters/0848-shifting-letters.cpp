class Solution {
public:
    string shiftingLetters(string s, vector<int>& shifts) {
        long long diff = 0;
        int n = s.length();
        for(int i=n- 1;i>=0;i--){
            diff+=shifts[i];
            s[i] = (((s[i]-'a') + diff) % 26 + 'a');
        }
        return s;
    }
};