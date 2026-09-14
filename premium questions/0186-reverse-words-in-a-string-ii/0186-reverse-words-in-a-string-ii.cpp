class Solution {
public:
    void reverseWords(vector<char>& s) {
        int n = s.size();
        int li = 0, ri = n - 1;
        while (li < ri) {
            swap(s[li], s[ri]);
            li++, ri--;
        }
        for (int l = 0; l < n; l++) {
            int r = l;
            while (r < n && s[r] != ' ') r++;
            r--;
            int li = l, ri = r;
            while (li < ri) {
                swap(s[li], s[ri]);
                li++, ri--;
            }
            l = r + 1;
        }
    }
};