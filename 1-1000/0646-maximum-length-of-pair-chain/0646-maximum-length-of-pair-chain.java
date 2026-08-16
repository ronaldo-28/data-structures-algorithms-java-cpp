class Solution {
    int min=1000,max=-1000,count =0;
    int[] re;
    public int findLongestChain(int[][] pairs) {
        for(int[] pair : pairs){
          min = Math.min(min,pair[0]);  
          max = Math.max(max, pair[1]);
        }
        re = new int[max-min+1];
        Arrays.fill(re,-1);
        
        for(int[] pair : pairs){
            pair[0] = pair[0] - min;
            pair[1] = pair[1] - min;
            if(re[pair[1]] < pair[0]){
                re[pair[1]] = pair[0];
            }
        }

        for (int i = 1,start=-1; i < max-min+1; i++) {
            if(start < re[i]){
                start = i;
                count++;
            }
        }
        return count;
    }
}