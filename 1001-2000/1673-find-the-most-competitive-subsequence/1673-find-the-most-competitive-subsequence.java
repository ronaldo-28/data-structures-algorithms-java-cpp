class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        int[] ans = new int[nums.length];
        int top = -1;
        int canDelete = nums.length - k;

        for(int num : nums){
            while(canDelete > 0 && top >= 0 && ans[top] > num ){
                top--;
                canDelete--;
            }

            ans[++top] = num;
        }
        return Arrays.copyOfRange(ans , 0 , k);
    }
}