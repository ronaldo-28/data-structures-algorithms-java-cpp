class Solution {
    public int minSwaps(int[] nums) {
        int even=0;
        int odd=0;
        int ecost=0;
        int ocost=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]%2==0){
                ecost+=Math.abs(i-(even*2));
                even++;
            }else{
                ocost+=Math.abs(i-(odd*2));
                odd++;
            }
        }
        if(Math.abs(even-odd)>1) return -1;
        if(even>odd) return ecost;
        if(even<odd) return ocost;
        return Math.min(ecost,ocost);
    }
}