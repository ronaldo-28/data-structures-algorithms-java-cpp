class Solution {
    public int minimumLines(int[][] points) {
        int len = points.length;
        HashMap<Integer, Integer> memoize = new HashMap<>();
        
        int target = 0;
        for(int i = 0; i < len; i++) {
            target = target | (1 << i);
        }
        
        return minimumLines(points, 0, memoize, target);
    }
    
    private int minimumLines(int[][] points, int key, HashMap<Integer, Integer> memoize, int target) {
        if (key == target) {
            return 0;
        }
        
        if (memoize.containsKey(key)) {
            return memoize.get(key);
        }
		
        // Find a point which is currently not covered by any previous lines
        int id = 0;
        for(int i = 0; i < points.length; i++) {
            if (!taken(i, key)) {
                id = i;
                break;
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            if (taken(i, key) || i == id) {
                continue;
            }
            
            int newKey = key | (1 << id);
            
            double slope = slope(points[id], points[i]);
            for(int j = 0; j < points.length; j++) {
                if (j == id || taken(j, newKey)) {
                    continue;
                }
                
                double slopeJ = slope(points[id], points[j]);
                if (slope == slopeJ) {
                    newKey |= (1 << j);
                }
            }
            
            ans = Math.min(ans, 1 + minimumLines(points, newKey, memoize, target));
        }
        
        ans = (ans == Integer.MAX_VALUE ? 1 : ans);

        memoize.put(key, ans);
        return ans;
    }
    
    private boolean taken(int i, int key) {
        return ((key >> i) & 1) == 1;
    }
    
    private double slope(int[] a, int[] b) {
        if (a[1] == b[1]) {
            return Double.MAX_VALUE;
        }
        
        return (1.0 * (b[0] - a[0])) / (b[1] - a[1]);
    }
}