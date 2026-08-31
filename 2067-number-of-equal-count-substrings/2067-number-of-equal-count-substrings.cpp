class Solution {
public:
// It could be tricky to figure out the approach, but once you get it - the implementation is quite simple.

// The idea is to fix the number of unique characters per substring, e.g. the substring must contain exactly 5 unique characters. This will allow us to use the sliding window approach. We will need to run the sliding window up to 26 times, for each number of exact unique characters.

// unique tracks the exact number of unique characters ([1..26]).
// The size of the sliding window is count * unique.
// Thanks summerzhou for this insightful observation.
// For each unique we run the sliding window algorithm:
// has_count is the number of unique characters that appear count (or more) times in the window.
// We move the right side to keep the window size the same, adjusting has_count.
// If unique characters has the right count (has_count == unique ), we increment the number of equal count substrings.
// Because the window size is fixed, this works - if one of the character appears more than count, the other will have to appear less.
    
    int equalCountSubstrings(string s, int count) {
        
        int res = 0, max_unique = unordered_set(begin(s), end(s)).size();
        
        for (int unique = 1; unique <= max_unique; ++unique) {
            int cnt[26] = {}, len = count * unique, has_count = 0;
            for (int i = 0; i < s.size(); ++i) {
                if (++cnt[s[i] - 'a'] == count)
                    ++has_count;
                if (i >= len && --cnt[s[i - len] - 'a'] == count - 1)
                    --has_count;
                res += has_count == unique;
            }
        }
        
        return res;
        
    }
};

















///////









////////




