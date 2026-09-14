class Solution {
    public int maxDistance(String[] words) {
        int n=words.length;
        if(!words[0].equals(words[n-1])){
            return n;
        }
        int maxDist=0;
        for(int i=1; i<n-1; i++){
            if(!words[i].equals(words[0])){
                int dist=Math.max(i+1,n-i);
                maxDist=Math.max(maxDist,dist);
            }
        }
        return maxDist;
    }
}