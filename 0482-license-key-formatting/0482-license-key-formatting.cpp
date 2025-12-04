class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
        string ss = "";
        int count = 0;

        for (int i = s.size() - 1; i >= 0; i--) {
            if (s[i] != '-') {
                if (count == k) {
                    ss.push_back('-');
                    count = 0;
                }
                ss.push_back(toupper(s[i]));
                count++;
            }
        }
        reverse(ss.begin(), ss.end());
        return ss;
    }
};