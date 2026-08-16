class Solution {
public:
    int nthUglyNumber(int n) {
        vector<int>s(n);
        s[0]=1;
        int next2=2,next3=3,next5=5;
        int i2=0,i3=0,i5=0;
        for(int i=1;i<n;i++){
            int next=min(next2,min(next3,next5));
            s[i]=next;
            if(next2==next){
                i2++;
                next2=s[i2]*2;
            }
            if(next3==next){
                i3++;
                next3=s[i3]*3;
            }
            if(next5==next){
                i5++;
                next5=s[i5]*5;
            }
        }
        return s[n-1];
    }
};