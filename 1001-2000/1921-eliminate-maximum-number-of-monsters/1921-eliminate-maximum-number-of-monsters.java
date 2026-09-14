class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] count = new int[n]; // Our "buckets"
        
        // 1. Populate the frequency array
        for (int i = 0; i < n; i++) {
            // Integer division trick to find the absolute latest minute 
            // we can safely shoot this specific monster.
            int maxShootMinute = (dist[i] - 1) / speed[i];
            
            // If the deadline is within the bounds of our game's timeframe, count it.
            if (maxShootMinute < n) {
                count[maxShootMinute]++;
            }
        }
        
        int monstersToKill = 0;
        
        // 2. Iterate through the timeframe
        for (int i = 0; i < n; i++) {
            monstersToKill += count[i];
            
            // At minute 'i', we have the capacity to shoot 'i + 1' monsters.
            // If the required kills exceed our capacity, the city is destroyed.
            if (monstersToKill > i + 1) {
                return i + 1; 
            }
        }
        
        // If we make it through the loop, we successfully eliminated all monsters.
        return n;
    }
}