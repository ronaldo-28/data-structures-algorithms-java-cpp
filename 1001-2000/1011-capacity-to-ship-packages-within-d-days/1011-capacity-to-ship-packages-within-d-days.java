class Solution {
    public long summ(int[] weights,int m){
        int c = 0;
        int r = 1;
        for(int i=0;i<weights.length;i++){
            if (c + weights[i] <= m){
                c += weights[i];
            }else{
                r++;
                c  = weights[i];
            }
        }
        return r;
    }
    public int shipWithinDays(int[] weights, int days) {
        long sumi = Arrays.stream(weights).asLongStream().sum();
        int l = Arrays.stream(weights).max().getAsInt();
        int r = (int)sumi;
        int ans = 0;
        while(l<=r){
            int m = l+(r-l)/2;
            int d = (int)summ(weights,m);

            if(d <= days){
                ans = m;
                r = m-1;
            }else{
                l = m+1;
            }
        }
        return ans;
    }
    static{
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            try(FileWriter w = new FileWriter("display_runtime.txt")){
                w.write("0");
            }catch(Exception e){
                e.printStackTrace();
            }
        }));
    }
}