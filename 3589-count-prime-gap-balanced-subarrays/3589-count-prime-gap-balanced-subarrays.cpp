class Solution {
public:
    int primeSubarray(vector<int>& nums, int k) {
        int n = nums.size();
        const int M = 50000;
        vector<char> esPrimo(M + 1, 1);
        esPrimo[0] = esPrimo[1] = 0;
        for (int i = 2; (long long)i * i <= M; i++)
            if (esPrimo[i])
                for (int j = i * i; j <= M; j += i) esPrimo[j] = 0;

        vector<int> maxQ(n), minQ(n);        // índices de primos
        int hMax = 0, tMax = 0, hMin = 0, tMin = 0;
        int left = 0, p1 = -1, p2 = -1;
        long long total = 0;

        for (int r = 0; r < n; r++) {
            if (esPrimo[nums[r]]) {
                while (tMax > hMax && nums[maxQ[tMax - 1]] <= nums[r]) tMax--;
                maxQ[tMax++] = r;
                while (tMin > hMin && nums[minQ[tMin - 1]] >= nums[r]) tMin--;
                minQ[tMin++] = r;
                p2 = p1;
                p1 = r;

                while (nums[maxQ[hMax]] - nums[minQ[hMin]] > k) {
                    if (maxQ[hMax] < minQ[hMin]) { left = maxQ[hMax] + 1; hMax++; }
                    else                         { left = minQ[hMin] + 1; hMin++; }
                }
            }
            if (p2 != -1 && p2 >= left) total += p2 - left + 1;
        }
        return (int)total;
    }
};