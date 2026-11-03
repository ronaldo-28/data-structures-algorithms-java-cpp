class Solution {
public:
    bool hasSpecialSubstring(std::string s, int k) {
        int n = s.size();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s[j] == s[i]) ++j;
            if (j - i == k) return true;
            i = j;
        }
        return false;
    }
};