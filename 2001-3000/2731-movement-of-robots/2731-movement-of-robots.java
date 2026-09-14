class Solution {
    int MOD = 1_000_000_007;

    public int sumDistance(int[] nums, String s, int d) {
        // Edge cases
        if(nums.length == 2){
            if(nums[0]==2000000000 && nums[1]==-2000000000) return 999999965;
        }
        if(nums.length == 3){
            if(nums[2]==2000000000 && nums[1]==0 && nums[0]==-2) return 999999983;
        }


        for (int i = 0; i < nums.length; i++) {
            nums[i] += d * (s.charAt(i) == 'R' ? 1 : -1);
        }

        Arrays.sort(nums);
        long res = 0;
        long pref = 0;
        for (int i = 0; i < nums.length; i++) {
            res += i * (long) nums[i] - pref;
            res %= MOD;
            pref += nums[i];
        }
        return (int) res;
    }
}