class Solution {
    int dp[10][21][21];
    int solve(int pos, bool tight,bool is_leading_zeros,int count, int remainder,const string& num, int k){
        if(pos == num.size()) return count == 10 && remainder == 0;
        if(!tight && !is_leading_zeros && dp[pos][count][remainder] != -1) return dp[pos][count][remainder];
        int upper_limit = tight ? num[pos] - '0': 9;
        int total = 0;

        for(int d = 0; d <= upper_limit; d++){
            bool new_tight = tight && d == upper_limit;
            bool new_is_leading_zeros = is_leading_zeros && (d == 0);
            int new_count = (d & 1)
                  ? count - 1
                  : (new_is_leading_zeros ? 10 : count + 1);
            int new_remainder =  (remainder*10 +  d) % k; 
            total += solve(pos+1,new_tight,new_is_leading_zeros,new_count,new_remainder,num,k);
        }
        if(!tight && !is_leading_zeros) dp[pos][count][remainder] = total;
        return total;
    }
public:
    int numberOfBeautifulIntegers(int low, int high, int k) {
        memset(dp, -1, sizeof(dp));
        string high_str = to_string(high);
        int right =  solve(0,true,true,10,0,high_str,k);
        memset(dp, -1, sizeof(dp));
        string low_str = to_string(low-1);
        int left = solve(0,true,true,10,0,low_str,k); 

        return right - left;

    }
};