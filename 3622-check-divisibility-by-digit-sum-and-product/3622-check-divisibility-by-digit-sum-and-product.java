class Solution {
    public boolean checkDivisibility(int n) {
        int sum=0;
        int pro=1;
        int original=n;
        while(n!=0)
        {
            int last=n%10;
            sum=sum+last;
            pro=pro*last;
            n/=10;
        }

        int result=sum+pro;
        if(original%result==0)
        {
            return true;
        }

        return false;
    }
}