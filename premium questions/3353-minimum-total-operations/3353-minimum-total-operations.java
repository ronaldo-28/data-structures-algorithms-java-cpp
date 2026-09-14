class Solution {
    public int minOperations(int[] nums) {
       int currentNumber = nums[0];
       int countOfDuplicates = 0;
       for (int i = 1; i < nums.length; i++) {
          if (currentNumber == nums[i]) {
            countOfDuplicates++;
            // System.out.println(countOfDuplicates + "  shit " + nums[i]);
          } 

          currentNumber = nums[i];
          
       }

       return nums.length - countOfDuplicates - 1 ;
    }
}