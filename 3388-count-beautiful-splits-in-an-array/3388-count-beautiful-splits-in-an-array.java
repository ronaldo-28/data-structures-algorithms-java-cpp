class Solution {
    public int beautifulSplits(int[] nums) {
        int n=nums.length;
        int[][] lcp=new int[n+1][n+1];

        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>i;j--){
                if(nums[i]==nums[j]){
                    lcp[i][j]=1+lcp[i+1][j+1];
                }
            }
        }

        int ans=0;

        for(int i=1;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                int len=j-i;

                if((i<=len && lcp[0][i]>=i) ||
                   (len<=n-j && lcp[i][j]>=len)){
                    ans++;
                }
            }
        }

        return ans;
    }
}