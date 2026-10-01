class Solution {
public:
    int maximumPrimeDifference(vector<int>& nums) {
        // Sieve of Eratoathenes
        vector<bool> prime(101, 1);
        prime[0] = 0;
        prime[1] = 0;
        for (int i = 2; i * i <= 100; i++) {
            if (prime[i] == 1) {
                for (int j = i * i; j <= 100; j += i)
                    prime[j] = 0;
            }
        }

        int ptr1 = 0, ptr2 = nums.size() - 1;
        while (ptr1 < nums.size() && prime[nums[ptr1]] == 0)
            ptr1++;
        while (ptr2 >= 0 && prime[nums[ptr2]] == 0)
            ptr2--;
        return ptr2 - ptr1;
    }
};