class Solution {
    public long numOfSubsequences(String s) {
        long totalT = 0;
        char[] sChars = s.toCharArray();
        for(char c: sChars){
            if(c == 'T') totalT++;
        }
        long l = 0, lc = 0, lct = 0, c = 0, t = 0, ct = 0;
        long maxGainC = 0;
        for(char e: sChars){
            if(e == 'L'){
                l++;
            }else if(e == 'C'){
                lc += l;
                c++;
            }else if(e == 'T'){
                lct += lc;
                ct += c;
                t++;
            }
            maxGainC = Math.max(maxGainC, l*(totalT-t));
        }
        return lct + Math.max(maxGainC, Math.max(lc, ct));
    }
}