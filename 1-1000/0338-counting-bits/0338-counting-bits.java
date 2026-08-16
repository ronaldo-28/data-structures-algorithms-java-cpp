class Solution {
    
    public void helper(int[] ans,int count,int n){
        if(n>=ans.length) return;
        ans[n] = count;
        n=n<<1;
        helper(ans,count+1,n+1);
        helper(ans,count,n);
    }
    public int[] countBits(int n) {
        int ans[] = new int[n+1];
        helper(ans,1,1);
        return ans;
    }
}