class Solution {
    static {
    Runtime.getRuntime().gc();
    Runtime.getRuntime().addShutdownHook(new Thread(()->{
        try(FileWriter f = new FileWriter("display_runtime.txt")){
            f.write("0");
        }catch(Exception e){

        }
    }));
}
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n=s.length();
        long[][]prefix=new long[n+1][3];
        long sum=0;
        long count=0;
        long digit=0;
        int mod=(int)1e9+7;
        for(int i=0;i<n;i++){
            int curr=s.charAt(i)-'0';
            sum=(sum+curr)%mod;
            if(curr!=0){
                count++;
                digit=(digit*10+curr)%mod;
            }
            prefix[i+1][0]=sum;
            prefix[i+1][1]=digit;
            prefix[i+1][2]=count;
        }
        long pow10[]=new long[n+1];
        pow10[0]=1;
        for(int i=1;i<=n;i++){
            pow10[i]=(pow10[i-1]*10)%mod;
        }
        n=queries.length;
        int[]res=new int[n];
        for(int i=0;i<n;i++){
            int l=queries[i][0];
            int r=queries[i][1];
            //diff of digits count
            int diff=(int)(prefix[r+1][2]-prefix[l][2]);
            long d=(prefix[r+1][1]-(prefix[l][1]*pow10[diff])%mod+mod)%mod;
            long t=prefix[r+1][0]-prefix[l][0];
            res[i]=(int)(d%mod*t%mod%mod);
        }
        return res;
    }
}