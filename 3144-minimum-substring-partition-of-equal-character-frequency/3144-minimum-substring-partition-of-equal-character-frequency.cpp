class Solution {
public:
    int minimumSubstringsInPartition(string s) {
        int n = s.length();
        vector<int> f(n+1, INT_MAX);
        // recursion boundar: no element to sum, so sum = 0
        f[0] = 0;
        for(int i=0; i<n; i++){
            // have to initilaized
            int alphabetCnt[26] = {}, k = 0, maxCnt = 0;
            for(int j = i ; j >= 0; j--){
                int c = s[j] - 'a';
                if( alphabetCnt[c] == 0 )
                    k++;
                alphabetCnt[c]++;
                maxCnt = max( maxCnt, alphabetCnt[c] );
                if( i - j + 1 == k * maxCnt )
                    f[i+1] = min( f[i + 1], f[j] + 1 );
            }
        }
        return f[n];
    }
};