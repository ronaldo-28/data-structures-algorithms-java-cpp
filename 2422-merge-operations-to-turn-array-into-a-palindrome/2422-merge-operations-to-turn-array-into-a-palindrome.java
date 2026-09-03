class Solution {
    // time = O(n), space = O(1)
    public int minimumOperations(int[] nums) {
        int n = nums.length, left = nums[0], right = nums[n - 1], cnt = 0;
        for (int i = 0, j = n - 1; i < j;) {
            if (left == right) {
                i++;
                j--;
                left = nums[i];
                right = nums[j];
            } else if (left < right) {
                i++;
                left += nums[i];
                cnt++;
            } else if (left > right) {
                j--;
                right += nums[j];
                cnt++;
            }
           // System.out.println("left:"+left+", right:"+right);
        }
        return cnt;
    }
}