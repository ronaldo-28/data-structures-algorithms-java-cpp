class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 <<k;
        int n = s.length();

        if(n<k || n-k+1 < need)
        return false;

        boolean[] seen = new boolean[need];
        int hash = 0;
        int allOnes = need -1;
        int count = 0;

        for(int i=0; i<n; i++) {
            hash = ((hash << 1) & allOnes) | (s.charAt(i) - '0');
             if(i >= k-1){
                if(!seen[hash]){
                    seen[hash]=true;
                    count++;
                    if(count == need)
                    return true;
                }
             }
        }
        return false;
    }
}