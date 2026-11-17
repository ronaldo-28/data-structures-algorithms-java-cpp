class Solution {
    public int[] longestCommonPrefix(String[] words) {
        int n=words.length, maxI=-1,maxJ=-1,secI=-1,secJ=-1, max=0, sec=0;
        for(int i=1;i<n;i++){
            int common=0,m=Math.min(words[i].length(),words[i-1].length());
            for(int j=0;j<m;j++){
                if(words[i].charAt(j)==words[i-1].charAt(j)){
                    common++;
                }
                else break;
            }
            if(common>max){
                sec=max;
                secI=maxI;
                secJ=maxJ;
                max=common;
                maxI=i-1;
                maxJ=i;
            }
            else if(common>sec){
                sec=common;
                secI=i-1;
                secJ=i;
            }
        }
        int[] res=new int[n];
        for(int i=0;i<n;i++){
            int common=0;
            if(i>0 && i<n-1){
                int m=Math.min(words[i-1].length(),words[i+1].length());
                for(int j=0;j<m;j++){
                    if(words[i-1].charAt(j)==words[i+1].charAt(j)){
                        common++;
                    }
                    else break;
                }
            }
            if(maxI==i || maxJ==i){
                res[i]=sec;
            }
            else {
                res[i]=max;
            }
            res[i]=Math.max(common,res[i]);
        }
        return res;
    }
}