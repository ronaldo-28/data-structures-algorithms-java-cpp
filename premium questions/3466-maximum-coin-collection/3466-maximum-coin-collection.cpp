class Solution {
public:
    long long maxCoins(vector<int>& lane1, vector<int>& lane2) {
    long long res = -1e14, l1 = -1e14, l2 = -1e14, l21 = -1e14;
    for (int i = 0; i < lane1.size(); ++i) {
        l1 = max(0LL, l1) + lane1[i];
        l2 = max(max(0LL, l2) + lane2[i], l1);
        l21 = max(l21 + lane1[i], l2);
        res = max({res, l1, l21});
    }
    return res;
}
};