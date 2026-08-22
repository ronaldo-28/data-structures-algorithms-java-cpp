class Solution {
    public int sumOfDigits(int[] nums) {
    int min = Integer.MAX_VALUE;

    for (int i = 0; i < nums.length; i++){
        min = Math.min(min, nums[i]);
    }

    int sum = 0;
    
    while (min > 0){
    sum += (min % 10);
    min /= 10;
    }

    if (sum % 2 == 0) return 1;
    return 0;
    }
}