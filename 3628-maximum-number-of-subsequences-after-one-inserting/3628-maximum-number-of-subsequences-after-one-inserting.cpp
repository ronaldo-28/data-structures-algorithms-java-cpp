class Solution {
public:
    long long numOfSubsequences(string s) {
        int n=s.size();

        vector<long long> preL(n,0);
        vector<long long> sufT(n,0);

        long long l=0,lc=0,c=0;
        long long countLCT=0,countCT=0;

        for(int i=0;i<n;i++) {
            if(s[i]=='L') {
                l++;
            }
            else if(s[i]=='C') {
                lc+=l;
                c++;
            }
            else if(s[i]=='T') {
                countLCT+=lc;
                countCT+=c;
            }

            preL[i]=l;
        }

        long long old_answer=countLCT;

        long long t=0;

        for(int i=n-1;i>=0;i--) {
            if(s[i]=='T')
                t++;

            sufT[i]=t;
        }

        // Insert L at beginning
        long long ans1=old_answer+countCT;

        // Insert T at end
        long long ans3=old_answer+lc;

        // Insert C
        long long ans2=old_answer;

        for(int i=0;i<n;i++) {
            long long x;

            if(i==0)
                x=0*sufT[i];
            else
                x=preL[i-1]*sufT[i];

            ans2=max(ans2,old_answer+x);
        }

        return max({ans1,ans2,ans3});
    }
};