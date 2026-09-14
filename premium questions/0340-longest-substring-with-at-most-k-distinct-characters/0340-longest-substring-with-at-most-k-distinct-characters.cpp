class Solution {
public:
    int lengthOfLongestSubstringKDistinct(string s, int k) {
    int ctr[256] = {}, j = -1, distinct = 0, maxlen = 0;
    for (int i=0; i<s.size(); ++i) {
        distinct += ctr[s[i]]++ == 0;
        while (distinct > k)
            distinct -= --ctr[s[++j]] == 0;
        maxlen = max(maxlen, i - j);
    }
    return maxlen;
}
};