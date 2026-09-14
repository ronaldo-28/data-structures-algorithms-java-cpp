class Solution {
    public int find(int[] m){
        int low =0;
        int high = m.length-1;
        int ans = -1;
        while(low<=high){
            int mid = (low+high)/2;
            if(m[mid]==1){
                ans = mid;
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        return ans+1;
    }
    public int min(int[] m){
        int ans = Integer.MAX_VALUE;
        int ind = -1;
        for(int i =0;i<m.length;i++){
            if(ans>m[i]){
                ans = m[i];
                ind = i;
            }
        }
        return ind;
    }
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] ans = new int[k];
        int[] dupli = new int[mat.length];
        for(int i=0;i<mat.length;i++){
           dupli[i] = find(mat[i]);

        }
        for(int j= 0;j<k;j++){
            int val = min(dupli);
            ans[j] = val;
            dupli[val] = Integer.MAX_VALUE;
        }
        return ans;
    }
}