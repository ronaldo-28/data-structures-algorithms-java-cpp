class Solution {
    public int minOperations(int[] nums) {
        int m = nums.length;

        int mx = Integer.MIN_VALUE, mn = Integer.MAX_VALUE;
        for (int f : nums) {
            if (f > mx) mx = f;
            if (f < mn) mn = f;
        } 

        int[] map = new int[mx - mn + 1];

        int unique = 0;
        for (int f : nums) {
            int g = ++map[f - mn];

            if (g == 1) unique++;
        } 

        int cnt = 0, runUnique = 0;
        for (int f : nums) {
            int g = map[f - mn];
            
            if (g == 0) continue;
            else if (g == 1) return -1;
            else {
                cnt += (g + 2) / 3;
                map[f - mn] = 0;
                runUnique++;
            } 

            if (runUnique == unique) return cnt;
        }

        return cnt;
    }
}