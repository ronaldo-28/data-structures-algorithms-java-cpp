class Solution {
    public boolean stoneGame(int[] pl) {
        int an=0;
        int bm=0;
        int re=1,ln=pl.length;
        for(int i=0;i<ln;i++){
            if(re%2!=0){
                an+=Math.max(pl[i],pl[ln-i-1]);
            }
            else{
                bm+=Math.min(pl[i],pl[ln-i-1]);
            }
            re++;
        }
        if(bm<an){
            return true;
        } 
        return false;
    }
}