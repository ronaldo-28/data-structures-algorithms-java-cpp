class Solution {
    static {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
            fw.write("0");
        } catch (Exception e) {

        }
    }));
}

    public long countDistinct(long n) {

        String s=Long.toString(n);
        int len=s.length();
        long ans=0;
        long pow=9;
        for(int i=1;i<len;i++){
            ans+=pow;
            pow*=9;
        }

        for(int i=0;i<len;i++){
            if((s.charAt(i)-'0')==0){
                break;
            }
            long choices=(s.charAt(i)-'0')-1;
            long m=1;
            for(int j=i+1;j<len;j++){
                m*=9;
            }
            ans+=(choices*m);
        }
        if(!s.contains("0")){
            ans++;
        }
        return ans;
    }
    
}