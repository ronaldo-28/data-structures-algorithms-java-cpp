class Solution {
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        int[] hCuts =  new int[1001];
        Arrays.fill(hCuts, 0); 
        for (int h: horizontalCut) hCuts[h]++;

        int[] vCuts =  new int[1001];
        Arrays.fill(vCuts, 0); 
        for (int v: verticalCut) vCuts[v]++;

        int hParts = 1;
        int vParts = 1;
        long sum = 0;
        
        for (int i = 1000;i>0;i--){
            sum += (long) i * vParts * hCuts[i];
            hParts += hCuts[i];
            
            sum += (long) i * hParts * vCuts[i];
            vParts += vCuts[i];
        }
        
        return sum;
    }
}