class Solution {
    public int heightChecker(int[] heights) {
        int n = heights.length;
        int[] count = new int[101];
        for(int height : heights){
            count[height]++;
        }

        int idx = 0;
        int res = 0;
        for(int i=0; i<n; i++){
            while(idx < 101 && count[idx] == 0) idx++;
            if(heights[i] != idx) res++;
            count[idx]--;
        }
        return res;

        
        // Find the minimum and maximum values in the array.
        //int minVal = Arrays.stream(arr).min().getAsInt();
        //int maxVal = Arrays.stream(arr).max().getAsInt();
    }
}