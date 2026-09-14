class Solution {
    private String helper(char[] chr, int end, int count){
        int index=0;
        for (int i=0;i<end;i++){
            if (chr[i]=='R'){
                if (count>=0){
                    chr[index++]='R';
                }
                count++;
            }else{
                if (count<=0){
                    chr[index++]='D';
                }
                count--;
            }
        }
        if (Math.abs(count)>=index){
            return (count>0) ? "Radiant" : "Dire";
        }
        return helper(chr, index, count);
    }
    public String predictPartyVictory(String senate) {
        return helper(senate.toCharArray(), senate.length(), 0);
    }
}