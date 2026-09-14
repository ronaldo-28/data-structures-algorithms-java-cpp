class Solution {
    // Memoization table: pos, ic, ec, mask
    Integer[][][][] memo;
    int m, n;
    int[] pow3;

    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        this.m = m;
        this.n = n;
        this.memo = new Integer[m * n][introvertsCount + 1][extrovertsCount + 1][(int) Math.pow(3, n)];
        
        // Pre-calculate powers of 3 for mask manipulation
        this.pow3 = new int[n + 1];
        pow3[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow3[i] = pow3[i - 1] * 3;
        }

        return solve(0, introvertsCount, extrovertsCount, 0);
    }

    private int solve(int pos, int ic, int ec, int mask) {
        // Base case: all cells have been considered
        if (pos == m * n) {
            return 0;
        }
        
        if (memo[pos][ic][ec][mask] != null) {
            return memo[pos][ic][ec][mask];
        }

        int row = pos / n;
        int col = pos % n;

        // Option 1: Leave the current cell empty
        int newMask = (mask * 3) % pow3[n]; // Shift the mask to the left
        int maxHappiness = solve(pos + 1, ic, ec, newMask);

        // Get states of top and left neighbors from the mask
        int topNeighbor = mask / pow3[n - 1];
        int leftNeighbor = (col == 0) ? 0 : (mask % 3);

        // Option 2: Place an introvert
        if (ic > 0) {
            int happiness = 120;
            int newMaskIntro = (newMask + 1);
            
            // Interaction with top neighbor
            if (topNeighbor != 0) {
                happiness -= 30; // Introvert loses 30
                if (topNeighbor == 1) happiness -= 30; // Neighbor introvert also loses 30
                else happiness += 20; // Neighbor extrovert gains 20
            }
            // Interaction with left neighbor
            if (leftNeighbor != 0) {
                happiness -= 30; // Introvert loses 30
                if (leftNeighbor == 1) happiness -= 30; // Neighbor introvert also loses 30
                else happiness += 20; // Neighbor extrovert gains 20
            }
            maxHappiness = Math.max(maxHappiness, happiness + solve(pos + 1, ic - 1, ec, newMaskIntro));
        }
        
        // Option 3: Place an extrovert
        if (ec > 0) {
            int happiness = 40;
            int newMaskExtro = (newMask + 2);

            // Interaction with top neighbor
            if (topNeighbor != 0) {
                happiness += 20; // Extrovert gains 20
                if (topNeighbor == 1) happiness -= 30; // Neighbor introvert loses 30
                else happiness += 20; // Neighbor extrovert also gains 20
            }
            // Interaction with left neighbor
            if (leftNeighbor != 0) {
                happiness += 20; // Extrovert gains 20
                if (leftNeighbor == 1) happiness -= 30; // Neighbor introvert loses 30
                else happiness += 20; // Neighbor extrovert also gains 20
            }
            maxHappiness = Math.max(maxHappiness, happiness + solve(pos + 1, ic, ec - 1, newMaskExtro));
        }
        
        return memo[pos][ic][ec][mask] = maxHappiness;
    }
}