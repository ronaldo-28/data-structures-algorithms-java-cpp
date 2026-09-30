class Solution {
    public long maxValue(int[] nums) {
        int n = nums.length;
        long pref = 0;
        long delta = 0;
        long eidx = -1000000000000L, oidx = 0;
        // hack is: max delta could be achievable on same parity
        for(int i=0; i<n; i++) {
            if((i&1) > 0) {
                pref -= nums[i];
                delta = Math.max(delta, oidx - pref);
                oidx = Math.max(oidx, pref);
            } else {
                pref += nums[i];
                delta = Math.max(delta, eidx - pref);
                eidx = Math.max(eidx, pref);
            }
        }
        return pref + 2*delta;
    }
}