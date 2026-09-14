class Solution {
public:
    string makeAntiPalindrome(string s) {
        // Counting
        vector<int> counts(26, 0);
        for (const char c : s)
        {
            ++counts[c - 'a'];
        }

        // Left part
        string ans(s.size(), ' ');
        for (int i = 0; i < s.size() / 2; ++i)
        {
            int j = 0;
            // Greedily find the one to be used in the left part.
            for (; j < 26; ++j)
            {
                if (counts[j] > 0)
                {
                    --counts[j];
                    break;
                }
            }
            ans[i] = j + 'a';
        }


        // Right part
        for (int i = s.size() / 2, k = s.size() / 2 - 1; i < s.size(); ++i, --k)
        {
            int j = 0;
            for (; j < 26; ++j)
            {
                if (counts[j] > 0 && ans[k] != j + 'a')
                {
                    --counts[j];
                    break;
                }
            }
            // It means there is no char found that could be inserted in the right part
            if (j == 26)
            {
                return "-1";
            }
            ans[i] = j + 'a';
        }

        return ans;
    }
};