class Solution {
    static boolean []si = new boolean[102];
    static{
        Arrays.fill(si,true);
        si[0] = false;
        si[1] = false;
        for(int i=2;i*i<101;i++){
            if(si[i]){
                for(int j=i*i;j<101;j+=i){
                    si[j] = false;
                }
            }
        }
        
    }
    public int maximumPrimeDifference(int[] nums) {
        int f = 0;
        int l =0;
        for(int i=0;i<nums.length;i++){
            if(si[nums[i]]){
                f =i;
                break;
            }
        }
        for(int i=nums.length-1;i>=0;i--){
            if(si[nums[i]]){
                l = i;
                break;
            }
        }
        return l-f;
    }
}