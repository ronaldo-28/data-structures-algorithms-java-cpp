class Solution {
public:
    typedef unsigned long long u64;
    int lengthAfterTransformations(string s, int t) {
        u64* alens = new u64[t+26];
        for (int i = 0; i < 26; i++) // length 1 for each. starting from a
            alens[i] = (unsigned)1;

        for (u64 i = 26; i < t + 26; i++)
            alens[i] = (alens[i-26] + alens[i-25]) % (u64)(1e9 + 7);

        u64 total = 0;
        for (unsigned char c : s)
            total = (total + alens[t + (c - 'a')]) % (u64)(1e9 + 7);
        
        return total;
    }
};