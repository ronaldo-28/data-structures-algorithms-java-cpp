
class Solution {
public:
    vector<long long> countKConstraintSubstrings(string s, int k, vector<vector<int>>& queries) {
        int n = s.size();

        vector<int> nextInvalid(n, n);
        vector<long long> prefix(n + 1, 0);

        int zero = 0, one = 0;
        int left = 0;

        // Step 1: Sliding window + prefix sum
        for (int right = 0; right < n; right++) {
            if (s[right] == '0') {
                zero++;
            } else {
                one++;
            }

            while (zero > k && one > k) {
                nextInvalid[left] = right;

                if (s[left] == '0') {
                    zero--;
                } else {
                    one--;
                }
                left++;
            }

            prefix[right + 1] = prefix[right] + right - left + 1;
        }

        // Step 2: Answer queries
        vector<long long> ans;

        for (auto &q : queries) {
            int l = q[0];
            int r = q[1];

            int p = min(r + 1, nextInvalid[l]);

            long long len = p - l;

            // Valid substrings starting at l
            long long first = len * (len + 1) / 2;

            // Valid substrings ending from p to r
            long long second = prefix[r + 1] - prefix[p];

            ans.push_back(first + second);
        }

        return ans;
    }
};
