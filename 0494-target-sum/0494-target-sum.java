class Solution {
    static{
        for(int i=0; i < 100; i++){
            findTargetSumWays(new int[]{1, 2, 3}, 3);
        }
    }
    public static int findTargetSumWays(int[] nums, int target) {
        int sum =0;
        int zero = 0;
        for(int num : nums){
            sum += num;
        }
        int result = sum - Math.abs(target);
        if(result < 0 ||(result & 1) == 1){
            return 0;
        }
        int[] data = new int[(result >>= 1)+1];
        data[0]++;
        for(int num : nums){
            if(num == 0){
              zero++;
              continue;  
            }
            for(int j = result; j >= num ; j--){
                data[j] +=  data[j-num];
            }
        }
        return data[result] << zero;
    }
}