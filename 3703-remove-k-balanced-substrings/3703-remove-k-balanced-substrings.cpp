class Solution {
public:
    string removeSubstring(string s, int k) {
        vector<pair<char, int>> st;
        
        for (char c : s) {
            if (!st.empty() && st.back().first == c) {
                // Same character, increment count
                st.back().second++;
            } else {
                // Different character, push new entry
                st.push_back({c, 1});
            }
            
            // Check if the last two groups form a k-balanced substring
            int n = st.size();
            if (n >= 2 && st[n-2].first == '(' && st[n-2].second >= k &&
                st[n-1].first == ')' && st[n-1].second == k) {
                
                // Remove k opening parentheses
                st[n-2].second -= k;
                
                // Remove the closing parentheses group
                st.pop_back();
                
                // Remove the opening parentheses group if its count is now 0
                if (st.back().second == 0) {
                    st.pop_back();
                }
            }
        }
        
        // Reconstruct the result string from remaining groups
        string result = "";
        for (auto& p : st) {
            result += string(p.second, p.first);
        }
        
        return result;
    }
};