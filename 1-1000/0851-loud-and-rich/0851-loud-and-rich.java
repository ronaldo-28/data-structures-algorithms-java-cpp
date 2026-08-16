class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
    int n=quiet.length;
        int[]res=new int[n];
        for(int i=0; i<n; i++){
            res[i]=i;
        }
        boolean changed=true;
        while(changed){
            changed=false;
            for(int[] rich:richer){
                if(quiet[res[rich[0]]]<quiet[res[rich[1]]]){
                    res[rich[1]]=res[rich[0]];
                    changed=true;
                }
            }
        }
        return res;
    }
}