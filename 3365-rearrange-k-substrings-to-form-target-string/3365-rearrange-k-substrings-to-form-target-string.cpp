class Solution {
public:

    unsigned long long Fnv64Hash(const char* data, unsigned long byteCount, unsigned long long seed = 14695981039346656037ULL)
    {
        for (unsigned long i = 0; i < byteCount; i++)
            seed = 1099511628211ULL * (seed ^ (unsigned char)data[i]);
        return seed;
    }

    bool isPossibleToRearrange(string s, string t, int k) {
        unordered_map<unsigned long long, int> count;

        int strLen = s.length();
        int step = strLen / k;
        const char* str = s.c_str();
        for (int i = 0; i < strLen; i += step)
        {
            unsigned long long hs = Fnv64Hash(str + i, step);
            count[hs] ++;
        }

        str = t.c_str();
        for (int i = 0; i < strLen; i += step)
        {
            unsigned long long hs = Fnv64Hash(str + i, step);
            if (--count[hs] < 0)
                return false;
        }
        return true;
    }
};