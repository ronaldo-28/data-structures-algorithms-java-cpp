class Solution {
    public int minSubarray(int[] nums, int p) {
         int n = nums.length;
        long total = 0;
        for (int num : nums) total += num;
        if (total < p) return -1;

        long need = total % p;   
        if (need == 0) return 0; 
        for (int num : nums) {
            if (num % p == need) return 1;
        }

        int cap = Integer.highestOneBit(n + 1) << 2;
        int mask = cap - 1;

        long[] keys = new long[cap];  
        int[] vals  = new int[cap];  

        for (int i = 0; i < cap; i++) keys[i] = -1;

        keys[0] = 0;
        vals[0] = 0;
        int minSize = n;
        long prefix = 0;

        for (int i = 0; i < n; i++) {

            prefix = (prefix + nums[i]) % p;
            long target = prefix - need;
            if (target < 0) target += p;
            int h = hash(target, mask);
            while (keys[h] != -1) {
                if (keys[h] == target) {
                    int size = i + 1 - vals[h];
                    if (size < minSize) minSize = size;
                    break;
                }
                h = (h + 1) & mask;
            }
            h = hash(prefix, mask);
            while (keys[h] != -1 && keys[h] != prefix) {
                h = (h + 1) & mask;
            }
            keys[h] = prefix;
            vals[h] = i + 1;
        }

        return minSize < n ? minSize : -1;
    }

    private static int hash(long x, int mask) {
        return (int) ((x * 0x517cc1b727220a95L >>> 32) & mask);
    }
}