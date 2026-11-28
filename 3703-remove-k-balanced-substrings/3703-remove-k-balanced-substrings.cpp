pair<int,int> stk[100000];

class Solution {
public:
    string removeSubstring(string s, int k) {
        int n = 0, l = 0, r = 0, prv = '(';
        for (char c : s) {
            if (c == ')') {
                if (++r == k && l >= k && (r = 0, (l -= k) == 0) && n)
                    --n, l = stk[n].first, r += stk[n].second;
            }
            else if (r) stk[n++] = {l, r}, l = 1, r = 0;
            else ++l;
        }
        stk[n++] = {l, r};

        string res;
        for (int i = 0; i < n; ++i) {
            res.append(stk[i].first, '(');
            res.append(stk[i].second, ')');
        }
        return res;        
    }
};