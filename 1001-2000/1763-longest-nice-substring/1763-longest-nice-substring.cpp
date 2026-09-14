class Solution {
public:
    string longestNiceSubstring(string s) 
    {
        return string(solve(s));
    }

    string_view solve(string_view s)
    {
        if(s.length() < 2) return "";

        bitset<26> up, low;
        for(int i = 0; i < s.length(); i++)
        {
            if(islower(s[i])) low.set(s[i] - 'a');
            else up.set(s[i] - 'A');
        }

        for(int i = 0; i < s.length(); i++)
        {
            int ind = islower(s[i]) ? s[i]-'a' : s[i] - 'A';
            if(up.test(ind) ^ low.test(ind))
            {
                auto left = solve(s.substr(0, i));
                auto right = solve(s.substr(i + 1));

                return (left.length() >= right.length()) ? left : right;
            }
        }
        return s;

    }
};