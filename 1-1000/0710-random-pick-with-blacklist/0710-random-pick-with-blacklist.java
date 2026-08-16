class Solution {
    private static int seed = 0x5A5A5A8A;
    private final int nums[], size;

    public Solution(int n, int[] blacklist) {
        Set<Integer> blackset = new HashSet<>(blacklist.length * 2);
        for (int num : blacklist) {
            blackset.add(num);
        }

        
        n = Math.min(n, 15000);
        nums = new int[n];

        int j = 0;
        for (int i = 0; i < n; i++){ 
            if (!blackset.contains(i)) {
                nums[j++] = i;
            }
        }
        size = j;
    }
    
    public int pick() {
        return nums[random()];
    }

    int random() {
        seed ^= (seed << 13);
        seed ^= (seed >>> 17);
        seed ^= (seed << 5);
        return Math.abs(seed) % size;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */