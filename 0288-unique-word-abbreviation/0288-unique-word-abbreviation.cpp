// Solution 2: 3D static grid
class ValidWordAbbr {
    inline static long long table[26][26][20]; // characters length and string length
    constexpr static uint64_t FNV_OFFSET_BASIS = 0xcbf29ce484222325ULL;
    constexpr static uint64_t FNV_PRIME        = 0x100000001b3ULL;

    uint64_t fnv1a_64(const string& str)
    {
        uint64_t hash = FNV_OFFSET_BASIS;

        for (unsigned char c : str)
        {
            hash ^= c;
            hash *= FNV_PRIME;
        }

        return hash;
    }

    void createAbbr(string &str){
        const int n = str.size();

        long long hash = fnv1a_64(str);

        int firstChar = str[0] - 'a', lastChar = str[n - 1] - 'a', length = n - 2;

        long long &currentHash = table[firstChar][lastChar][length];

        if(currentHash == hash || currentHash == -1)
            currentHash = hash;
        else 
            currentHash = -2; // invalid entry
    }
public:
    ValidWordAbbr(vector<string>& dictionary) {
        
        memset(table, -1, sizeof(table));
        
        for(auto &word : dictionary){
            if(word.size() == 1) 
                continue;

            createAbbr(word);
        }
    }
    
    bool isUnique(string word) {
        const int n = word.size();
        if(n == 1) return true;

        int firstChar = word[0] - 'a', lastChar = word[n - 1] - 'a', length = n - 2;
        long long currentHash = table[firstChar][lastChar][length];

        if(currentHash == -1) return true; // unique entry
        if(currentHash == -2) return false; // invalid entry

        return fnv1a_64(word) == currentHash;
    }
};