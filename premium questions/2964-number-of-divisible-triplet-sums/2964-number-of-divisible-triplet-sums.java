class Solution {
    public int divisibleTripletCount(int[] nums, int d) {
       int ans = 0;
       int[] map = new int[d];
       for(int k = 2; k < nums.length; k++) {
        int num = (d - nums[k] % d) % d;
        for(int j = k-2; j >= 0; j--) {
            int r = (nums[j]+nums[k-1]) % d;
            map[r]++;
        }
        ans += map[num];
       } 
       return ans;
    }
}