class Solution {
public:
    bool isprime(int n){
        if(n<=1) return false;
        for(int i=2;i*i<=n;i++) if(!(n%i)) return false ;
        return true;
    }
    int maximumPrimeDifference(vector<int>& n) {
        ios_base::sync_with_stdio(false);
        cin.tie(NULL);
        int i=0,j=n.size()-1;
        while(!isprime(n[i])) i++;
        while(!isprime(n[j])) j--;
        return j-i;
    }
};