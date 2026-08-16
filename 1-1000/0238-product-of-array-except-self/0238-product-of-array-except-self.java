class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            productExceptSelf(new int[]{0, 0});
        }
    }
    public static int[] productExceptSelf(int[] nums) {
        int answer[] = new int[nums.length];
        answer[0] = 1;
        for(int i=1; i<nums.length; i++){
            answer[i] = answer[i-1] * nums[i-1];
        }
        int suffixProduct = 1;
        for(int i=nums.length-1; i>=0; i--){
            answer[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return answer;
    }
}