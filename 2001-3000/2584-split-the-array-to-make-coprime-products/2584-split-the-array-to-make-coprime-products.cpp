static constexpr int LIM = 1e6 + 1;
static int spf[LIM] = {0};
class sieve {
public:
    sieve() {
        for(int i = 2; i < LIM; ++i){
            if(!spf[i]){
                spf[i] = i;
                if(1LL * i * i < LIM){
                    for(int j = i * i; j < LIM; j += i){
                        if(!spf[j]) spf[j] = i;
                    }
                }
            }
        }
    }
} s;
class Solution {
public:
    int findValidSplit(vector<int>& nums) {
        const int n = nums.size();
        int last[LIM] = {0};
        for(int i = 0; i < n; ++i){
            int num = nums[i];
            while(num > 1){
                int p = spf[num];
                last[p] = i;
                while(num % p == 0) num /= p;
            }
        }
        int end = 0;
        for(int i = 0; i < n - 1; ++i){
            int num = nums[i];
            while(num > 1){
                int p = spf[num];
                end = max(end , last[p]);
                while(num % p == 0) num /= p;
            }
            if(i == end) return i;
        }
        return -1;
    }
};