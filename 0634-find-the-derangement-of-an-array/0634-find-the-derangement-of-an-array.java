class Solution {
    public int findDerangement(int n) {
        if(n<=1) return 0;
        else if(n==2) return 1;

        long a = 0;
        long b = 1;
        int mol = (int) 1e9+7;
        for(int i=3;i<=n;i++){
            long c = b;
            b = (i-1)*(a+b)%mol;
            a = c;
        }
        return (int)b;
    }
}