class Solution {
    public int maximumPossibleSize(int[] nums) {
        int result = 0;
        int min = Integer.MIN_VALUE;
        for (int num :  nums){
            if (num >= min){
                min = num;
                result ++;
            }
        }
        return result;
    }
}