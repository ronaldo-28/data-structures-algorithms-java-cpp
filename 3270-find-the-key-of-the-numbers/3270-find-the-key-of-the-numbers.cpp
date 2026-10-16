class Solution {
public:
    int generateKey(int num1, int num2, int num3) {
        int mini=min({num1,num2,num3});
        string ans="";
        while(mini>0){
            int x=num1%10;
            int y=num2%10;
            int z=num3%10;
            int to=min({x,y,z});
            ans+=(to+'0');
            mini=mini>>1;
            num1/=10;
            num2/=10;
            num3/=10;
        }
        reverse(ans.begin(),ans.end());
        return stoi(ans);
    }
};