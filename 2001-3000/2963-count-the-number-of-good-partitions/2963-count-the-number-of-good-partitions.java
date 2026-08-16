class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfGoodPartitions(int[] nums) {
        int n = nums.length;

        // Open-Addressing Linear Probing Map using bitwise masking
        // Use a power of 2 size >= 2 * n to keep hash collisions near zero
        int mapSize = 1;
        while (mapSize < n * 2) {
            mapSize <<= 1;
        }
        int mask = mapSize - 1;

        // Keys store the number values, values store the maximum right index seen
        int[] mapKeys = new int[mapSize];
        int[] mapValues = new int[mapSize];
        
        // Zero can be a valid input element, so track its value slot explicitly
        int zeroValueIdx = -1;

        // Step 1: Record the absolute last seen index position of every number in O(N)
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            if (val == 0) {
                zeroValueIdx = i;
                continue;
            }

            // High performance fractional key hash mixing
            int hash = (val ^ (val >>> 16)) & mask;
            while (mapKeys[hash] != 0 && mapKeys[hash] != val) {
                hash = (hash + 1) & mask;
            }
            mapKeys[hash] = val;
            mapValues[hash] = i; // Overwrite to guarantee maximum right boundary tracking
        }

        // Step 2: Linear sliding sweep to merge intervals and count components on-the-fly
        int totalPartitionsCount = 1;
        int maxRightBoundary = 0;

        for (int i = 0; i < n; i++) {
            // If the current index exceeds the previous max right boundary,
            // a complete independent segment block boundary has been crossed
            if (i > maxRightBoundary) {
                totalPartitionsCount = (totalPartitionsCount << 1);
                if (totalPartitionsCount >= MOD) {
                    totalPartitionsCount -= MOD; // Super fast subtraction modulo bypass
                }
            }

            int val = nums[i];
            int lastOccur;
            if (val == 0) {
                lastOccur = zeroValueIdx;
            } else {
                int hash = (val ^ (val >>> 16)) & mask;
                while (mapKeys[hash] != val) {
                    hash = (hash + 1) & mask;
                }
                lastOccur = mapValues[hash];
            }

            // Squeeze maximum boundary forward
            if (lastOccur > maxRightBoundary) {
                maxRightBoundary = lastOccur;
            }
        }

        return totalPartitionsCount;
    }
}

class Pair {
    int st;
    int end;
    Pair(int st, int end){
        this.st = st;
        this.end = end;
    }
}