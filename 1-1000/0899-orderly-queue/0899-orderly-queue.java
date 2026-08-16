class Solution {
    public String orderlyQueue(String s, int k) {
        if(k>1){
            char[]arr=s.toCharArray();
            Arrays.sort(arr);
            return new String(arr);
        }
            // K==1
            String dou=s+s;
            String sma=s;
            for(int i=0;i<s.length();i++){
                String str=dou.substring(i,i+s.length());

                if(str.compareTo(sma)<0){
                    sma=str;
                }
            }
            return sma;



    }
}