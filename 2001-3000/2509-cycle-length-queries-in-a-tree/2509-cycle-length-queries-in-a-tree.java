class Solution {
    private int findCycleLength(int u,int v){
        int pu = u;
        int pv = v;
        int ht1 = 0;
        int ht2 = 0;
        int lmb1 = 31 - Integer.numberOfLeadingZeros(pu);
        int lmb2 = 31 - Integer.numberOfLeadingZeros(pv);
        if(lmb1 > lmb2){
            pu = pu >> (lmb1 - lmb2);
            ht1 += lmb1 - lmb2;
        }
        else{
            pv = pv >> (lmb2 - lmb1);
            ht2 += lmb2 - lmb1;
        }
        while(pu != pv){
            pu = pu  >> 1;
            pv = pv >> 1;
            ht1++;
            ht2++;
        }
        return ht1 + ht2 + 1;
    }
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int m = queries.length;
        int ans[] = new int[m];
        for(int i = 0 ; i < m; i++){
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = findCycleLength(u,v);
        }
        return ans;
    }
}