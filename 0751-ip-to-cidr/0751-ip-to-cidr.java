class Solution {
    public List<String> ipToCIDR(String ip, int n) {
        int cur = toInt(ip);
        List<String> res = new ArrayList<>();
        while(n>0){
            int maxBits = Integer.numberOfTrailingZeros(cur);
            int maxAmount = 1<<maxBits;
            int bitVal = 1;
            int count = 0;
            while(bitVal< n && count< maxBits){
                bitVal<<=1;
                ++count;
            }
            if(bitVal>n){
                bitVal>>=1;
                --count;
            }
            res.add(toString(cur,32-count));
            n-= bitVal;
            cur+=(bitVal);
        }
        return res;
    }
    private String toString(int number, int range){
        //convert every 8 into an integer
        final int WORD_SIZE = 8;
        StringBuilder sb = new StringBuilder();
        for(int i=3; i>=0; --i){
           sb.append(Integer.toString(((number>>(i*WORD_SIZE))&255)));
           sb.append(".");
       }
        sb.setLength(sb.length()-1);
        sb.append("/");
        sb.append(Integer.toString(range));
        return sb.toString();
    }
    private int toInt(String ip){
        String [] sep = ip.split("\\.");
        int sum = 0;
        for(int i=0; i<sep.length;++i){
            sum*=256;
            sum+=Integer.parseInt(sep[i]);
        }
        return sum;
    }
}