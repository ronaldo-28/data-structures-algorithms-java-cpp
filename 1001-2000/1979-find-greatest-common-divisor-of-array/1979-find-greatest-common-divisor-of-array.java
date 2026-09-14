
class Solution {
    public int findGCD(int[] nums) {
        int mn=Integer.MAX_VALUE,mx=Integer.MIN_VALUE;for(int x:nums){mn=Math.min(mn,x);mx=Math.max(mx,x);}return gcd(mn,mx);
    }
    int gcd(int a,int b){return b==0?a:gcd(b,a%b);}
}