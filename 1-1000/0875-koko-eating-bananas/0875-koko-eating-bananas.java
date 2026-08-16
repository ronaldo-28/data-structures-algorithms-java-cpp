class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        long total = 0;
        for (int i = 0;i<n;i++) 
            total += piles[i];
        
        int left = (int) ((total - 1) / h) + 1;
        int right = (int) ((total - n) / (h - n + 1)) + 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int time = 0;
            for (int i = 0;i<n;i++) {
                time += (piles[i] - 1) / mid + 1;
            }
            if (time > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}