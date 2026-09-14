class Solution {
public:
    long long singleDivisorTriplet(vector<int>& nums) {
    long long res = 0, cnt[101] = {};
    for (int n : nums)
        ++cnt[n];
    for (int i = 1; i <= 100; ++i)
        for (int j = i; cnt[i] && j <= 100; ++j)
            for (int k = j + (i == k); cnt[j] && k <= 100; ++k) {
                int s = i + j + k;
                if (cnt[k] && (!(s % i) + !(s % j) + !(s % k) == 1)) {
                    if (i == j)
                        res += cnt[i] * (cnt[i] - 1) / 2 * cnt[k];
                    else if (j == k)
                        res += cnt[i] * cnt[j] * (cnt[j] - 1) / 2;
                    else 
                        res += cnt[i] * cnt[j] * cnt[k];
                }
            }
    return res * 6;
}
};