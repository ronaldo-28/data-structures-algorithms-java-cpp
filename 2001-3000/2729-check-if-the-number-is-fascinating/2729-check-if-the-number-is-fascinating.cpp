class Solution {
public:
    bool isFascinating(int n) {
        vector<int> freq(10, 0);
        int n1 = 2 * n;
        int n2 = 3 * n;

        for (int i = 0; i < 3; i++) {
            freq[n % 10]++;
            freq[n1 % 10]++;
            freq[n2 % 10]++;
            n /= 10;
            n1 /= 10;
            n2 /= 10;
        }

        if (freq[0] >= 1)
            return false;
        for (int i = 1; i < 10; i++)
            if (freq[i] > 1)
                return false;
        return true;
    }
};