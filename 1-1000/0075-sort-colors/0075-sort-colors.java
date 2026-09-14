class Solution {
    public void switchThem(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
    public void sortColors(int[] nums) {
        int read = 0;
        int zeroes = 0;
        int twos = nums.length - 1;
        
        
        while (read <= twos) {
            if (nums[read] == 0) {
                switchThem(nums, read, zeroes);
                read++;
                zeroes++;
            } else if (nums[read] == 2) {
                switchThem(nums, read, twos);
                twos--;
            } else {
                read++;
            }
        }
    }
}