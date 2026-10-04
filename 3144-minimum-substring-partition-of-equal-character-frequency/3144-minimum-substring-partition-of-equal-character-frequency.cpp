class Solution {
public:
    bool isBalanced(vector<int> &charFreq){
        int minFreq = 1001 , maxFreq = 0;
        for(int Freq : charFreq){
            if(Freq > 0){
                minFreq = min(minFreq , Freq);
                maxFreq = max(maxFreq , Freq);
            }
        }
        return minFreq == maxFreq;
    }

    int minimumSubstringsInPartition(string S){
        int N = S.size();
        vector<int> DP(N , N);
        for(int END = 0 ; END < N ; END++){
            vector<int> charFreq(26 , 0);
            for(int START = END ; START >= 0 ; START--){
                charFreq[S[START]-'a']++;
                if(isBalanced(charFreq)){
                    DP[END] = START > 0 ? min(DP[END] , 1 + DP[START - 1]) : 1;
                }
            }
        }
        return DP[N-1];
    }
};