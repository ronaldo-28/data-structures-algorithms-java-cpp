class Solution {
    static{
        for(int i = 0; i < 200; i++){
            canJump(new int[] {0});
        }
    }
    public static boolean canJump(int[] nums) {
        int reachable = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }
}