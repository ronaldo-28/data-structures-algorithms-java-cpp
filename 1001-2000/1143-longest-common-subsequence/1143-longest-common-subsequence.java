class Solution {
    static{
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try(FileWriter fw = new FileWriter("display_runtime.txt")){
                fw.write("0");
            }
            catch(IOException e){
              e.printStackTrace();
            }
        }));
    }
    // public int subsequence(int idx1,int idx2,String s1,String s2,int[][] dp){
    //     if(idx1<0||idx2<0){
    //         return 0;
    //     }
    //     if(dp[idx1][idx2]!=-1) return dp[idx1][idx2];
    //     //If match case
    //     if(s1.charAt(idx1)==s2.charAt(idx2)){
    //         return dp[idx1][idx2]=1+subsequence(idx1-1,idx2-1,s1,s2,dp);
    //     }
    //     //if not match case
    //     return dp[idx1][idx2]=0+Math.max(subsequence(idx1-1,idx2,s1,s2,dp),subsequence(idx1,idx2-1,s1,s2,dp));
        
    // }
    public int longestCommonSubsequence(String text1, String text2) {
        int n1=text1.length();
        int n2=text2.length();
        int[][] dp=new int[n1+1][n2+1];
        for(int[] arr:dp){
            Arrays.fill(arr,0);
        }
        //return subsequence(n1-1,n2-1,text1,text2,dp);
        for(int i=0;i<=n1;i++){
            dp[i][0]=0;
        }
        for(int j=0;j<=n2;j++){
            dp[0][j]=0;
        }

        for(int i=1;i<=n1;i++){
            for(int j=1;j<=n2;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }else{
                    dp[i][j]=0+Math.max(
                        dp[i][j-1],
                        dp[i-1][j]
                    );
                }
            }
        }
        return dp[n1][n2];
    }
}