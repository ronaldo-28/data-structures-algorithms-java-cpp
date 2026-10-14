class Solution {
public:
    string maxSumOfSquares(int num, int sum) {
        if (sum < 1 || sum > 9*num) return "";
        string res; res.reserve(num);
        int n = sum / 9, r = sum % 9;
        res.append(n, '9'); num -= n;
        if (r) res += '0' + r, --num;
        res.append(num, '0');
        return res;
    }
};