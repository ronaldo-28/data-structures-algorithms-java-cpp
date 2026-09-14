class Solution {
    public int countPairs(int[] nums, int low, int high) {
        return countToLim(nums, high+1) - countToLim(nums, low);
    }


    private int countToLim(int[] nums, int lim) { //counts up to lim exclusive by counting all that match some prefix of binary limit but remove exactly one bit (so they are lesser)
        int max = 0, n = nums.length;
        for(int num : nums) max = Math.max(num, max);
        /*gsb is greatest set bit, can early return if know that
        the greatest allowed to be set to stay < lim
        is greater than the max value in nums as this means
        that no xor can ever make high enough bits to exceed limit and
        thus all pairs are below them limit so return all (n choose 2)*/
        int gsb = 31 - Integer.numberOfLeadingZeros(lim-1);
        if(max < (1 << gsb)) return (n*(n-1))/2;
        int lsb = (lim & -lim);
        int[] cnt = new int[(1 << (32-Integer.numberOfLeadingZeros(max)))/lsb];
        for(int num : nums) cnt[num/lsb]++;
        int res = 0;
        lim /= lsb;
        while(--lim > 0) { //iterate prefix's of limit by their lsb
            lsb = (lim & -lim);
            int[] cnt2 = new int[cnt.length/lsb];
            for(int mask = 0; mask < cnt.length; mask++) {
                if(cnt[mask] == 0) continue;
                if(lsb > 0) cnt2[mask/lsb] += cnt[mask];
                res += cnt[mask]*cnt[lim ^ mask];
            }
            lim /= lsb;
            cnt = cnt2;
        }

        for(int mask = 0; mask < cnt.length; mask++) res += cnt[mask]*(cnt[mask]-1);
        return res/2;
    }

    
}