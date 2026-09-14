class Solution {
    public long maxRatings(int[][] units) {
        int[][] nums = units; 
        int m = nums.length;
        int n = nums[0].length;
        if (n == 1) {
            long totalSum = 0;
            for (int i = 0; i < m; i++) {
                totalSum += nums[i][0];
            }
            return totalSum;
        }
        
        long sumM2 = 0;
        int min1Global = Integer.MAX_VALUE;
        int min2Global = Integer.MAX_VALUE;
        
        for (int i = 0; i < m; i++) {
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            
            for (int j = 0; j < n; j++) {
                int val = nums[i][j];
                if (val < min1) {
                    min2 = min1;
                    min1 = val;
                } else if (val < min2) {
                    min2 = val;
                }
            }
            
            sumM2 += min2;
            if (min1 < min1Global) {
                min1Global = min1;
            }
            if (min2 < min2Global) {
                min2Global = min2;
            }
        }
        
        return min1Global + sumM2 - min2Global;
    }
}