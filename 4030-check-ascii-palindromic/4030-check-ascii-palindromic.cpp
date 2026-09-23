class Solution {
public:
    static bool isPalindromic(const std::string& s) noexcept {
        const auto sz = s.size();

        if ((sz & 1) && s[sz >> 1] != 'f') return false;

        for (auto forward = s.begin(), reverse = s.end() - 1; forward <= reverse; ++forward, --reverse) {
            switch (*forward) {
                case 'v': if (*reverse == 'n') continue; else return false;
                case 'n': if (*reverse == 'v') continue; else return false;
                case 'f': if (*reverse == 'f') continue; else return false;
                default: return false;
            }
        }
        return true;
    }
};