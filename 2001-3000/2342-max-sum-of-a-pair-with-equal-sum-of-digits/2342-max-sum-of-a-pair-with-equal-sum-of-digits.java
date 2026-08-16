class Solution {
    public int maximumSum(int[] nums) {
        int[] max = new int[100];
        int ans = -1;
        for(int x : nums){
            int dsum = 0;
            int temp = x;
            while(temp != 0){
                dsum += temp % 10;
                temp /= 10;
            }
            if(max[dsum] != 0) ans = Math.max(ans, x + max[dsum]);
            max[dsum] = Math.max(max[dsum], x);
        }
        return ans;
    }
}