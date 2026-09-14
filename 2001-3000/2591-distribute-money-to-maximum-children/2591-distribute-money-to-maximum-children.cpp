class Solution {
public:
    int distMoney(int money, int children) 
    {
        if(money<children) return -1;
        if(children*8<money) return children-1;
        money = money - children;
        int ans = 0;
        while(money>=7)
        {
            ans++;
            money=money-7;
        }
        if(money==3 && ans>0 && ans+1==children) ans--;
        return ans;
    }
//please upvote...
};