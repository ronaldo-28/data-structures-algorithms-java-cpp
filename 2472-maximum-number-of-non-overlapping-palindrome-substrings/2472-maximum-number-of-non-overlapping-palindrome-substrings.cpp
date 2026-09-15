class Solution {
public:
    int maxPalindromes(string s, int k) {
        int n=s.size(), ans=0, start=0;
        if(k==1) return n;
        for (int m=0;m<2*n;m++) {
            int l=m/2;
            int r=l+m%2;
            while (l>=start && r<n && s[l]==s[r]) {
                if (r+1-l>=k) {
                    ans++;
                    start=r+1;
                    break;
                }
                l--;r++;
            }
        }
        return ans;
    }
};