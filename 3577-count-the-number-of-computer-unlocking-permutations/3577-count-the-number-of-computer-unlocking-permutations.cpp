class Solution {
public:
    int MOD=1e9+7;
    int factorial(int x){
        int ret=1;
        for(int i=1;i<=x;i++){
            ret=(1LL*ret*i)%MOD;
        }
        return ret;
    }
    int countPermutations(vector<int>& complexity) {
        for(int i=1;i<complexity.size();i++){
            if(complexity[i]<=complexity[0])return 0;
        }
        int n=complexity.size();
        return factorial(n-1);
    }
};