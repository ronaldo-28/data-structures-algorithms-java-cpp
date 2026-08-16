// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
    static constexpr int DIGITS[] = {0, 1, 6, 8, 9};
    static constexpr int ROTATED[] = {0, 1, 9, 8, 6};

    static auto inc(std::string s) -> std::string {
        for (int i = static_cast<int>(s.size()) - 1; i >= 0; --i) {
            if (s[i] < '9') {
                s[i]++;
                return s;
            }
            s[i] = '0';
        }
        return "1" + s;
    }

    // NOLINTNEXTLINE(readability-function-cognitive-complexity)
    static auto count_lt(const std::string& up) -> int {
        int len = static_cast<int>(up.size());
        if (len == 0) {
            return 0;
        }

        int dp[20]{};
        int tot[20]{};
        dp[0] = 1;
        dp[1] = 3;
        for (int i = 2; i <= len; ++i) {
            dp[i] = (i & 1) != 0 ? dp[i - 1] * 3 : dp[i - 2] * 5;
        }
        tot[0] = 1;
        tot[1] = 4;
        for (int i = 2; i <= len; ++i) {
            tot[i] = 4 * dp[i - 2] + tot[i - 1];
        }
        for (int i = 0; i <= len; ++i) {
            tot[i]--;
        }

        int res = 0;
        std::string cur = up;

        for (int pos = 0; pos < (len + 1) / 2; ++pos) {
            bool mid = ((len & 1) != 0) && pos == len / 2;
            int ud = up[pos] - '0';

            int di = 0;
            for (; di < 5; ++di) {
                if (ud > DIGITS[di]) {
                    if (mid) {
                        res++;
                    } else if (pos == 0 && di == 0) {
                        res += tot[len - 1];
                    } else {
                        res += dp[len - 2 - 2 * pos];
                    }
                } else {
                    break;
                }
            }

            if (di < 5 && DIGITS[di] == ud) {
                cur[pos] = static_cast<char>('0' + DIGITS[di]);
                if (!mid) {
                    cur[len - 1 - pos] = static_cast<char>('0' + ROTATED[di]);
                }
            } else {
                return res;
            }
        }

        if (cur < up) {
            res++;
        }
        return res;
    }

   public:
    // NOLINTNEXTLINE(readability-identifier-naming)
    static auto strobogrammaticInRange(const std::string& low,
                                       const std::string& high) -> int {
        return std::max(0, count_lt(inc(high)) - count_lt(low));
    }
};