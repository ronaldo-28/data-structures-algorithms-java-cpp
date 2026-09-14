// 146
// to do using Dynamic Programming ...
class Solution {
public:
    const int mod=1e9+7;
    int numberOfWays(int n) {
        int count=0;
        for(int i=0;i*6<=n;i++) {
            for(int j=0;j<=2;j++) {
                int curr=6*i+j*4;
                if(curr<=n) {
                    int ways=(n-curr)/2+1;
                    count=(count + ways)%mod;
                }
            }
        }
        return count;
    }
};