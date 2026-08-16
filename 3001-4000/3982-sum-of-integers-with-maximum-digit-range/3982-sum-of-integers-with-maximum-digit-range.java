class Solution {
    public int maxDigitRange(int[] nums) {
        int maxRange = 0; 
        for(int x: nums) {
            int mx = 0, mn = 10; 

            int temp = x; 
            while(temp > 0) {
                int cur = temp % 10; 
                mx = Math.max(cur, mx); 
                mn = Math.min(cur, mn); 
                temp /=10; 
            }
            maxRange = Math.max(maxRange, mx - mn); 
        }
        // System.out.println(maxRange); 
        int ans = 0; 
        for(int x: nums) {
            int mx = 0, mn = 10; 
            int temp = x; 
            while(temp > 0) {
                int cur = temp % 10; 
                mx = Math.max(cur, mx); 
                mn = Math.min(cur, mn); 
                temp /=10; 
            }

            if(maxRange == (mx - mn)) ans += x; 
        }
        return ans; 
    }
}