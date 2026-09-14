class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] result = new int[2];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {  
                if (nums[i] == nums[j]) {
                    result[count] = nums[i];
                    count++;
                    break;  
                }
            }
        }     return result;
    }
}