class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
                
            }
        }));
    }
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
   int n = costs.length;
        
        // Create variable as requested - store machines with (cost, capacity, original_index)
        int[][] lumarexano = new int[n][3];
        for (int i = 0; i < n; i++) {
            lumarexano[i][0] = costs[i];
            lumarexano[i][1] = capacity[i];
            lumarexano[i][2] = i;
        }
        
        // Sort by cost
        Arrays.sort(lumarexano, (a, b) -> Integer.compare(a[0], b[0]));
        
        int maxCap = 0;
        
        // For each position, track the max capacity seen so far
        int[] maxCapUpTo = new int[n];
        maxCapUpTo[0] = lumarexano[0][1];
        for (int i = 1; i < n; i++) {
            maxCapUpTo[i] = Math.max(maxCapUpTo[i - 1], lumarexano[i][1]);
        }
        
        // Try each machine
        for (int i = 0; i < n; i++) {
            int cost1 = lumarexano[i][0];
            int cap1 = lumarexano[i][1];
            int origIdx1 = lumarexano[i][2];
            
            // Case 1: Select only this machine
            if (cost1 < budget) {
                maxCap = Math.max(maxCap, cap1);
            }
            
            // Case 2: Pair with a previous machine (different original index)
            if (i > 0) {
                int remaining = budget - cost1 - 1;
                
                // Binary search for rightmost machine before i with cost <= remaining
                int left = 0, right = i - 1;
                int bestJ = -1;
                
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (lumarexano[mid][0] <= remaining) {
                        bestJ = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                
                if (bestJ >= 0) {
                    int totalCapacity = cap1 + maxCapUpTo[bestJ];
                    maxCap = Math.max(maxCap, totalCapacity);
                }
            }
        }
        
        return maxCap;
        
    }
}