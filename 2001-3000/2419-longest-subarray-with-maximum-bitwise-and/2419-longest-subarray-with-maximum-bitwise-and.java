class Solution {
    public int longestSubarray(int[] nums) {
        
        int maxLen = 0;
        int maxEle = 0;
        
        int tempLen = 1;
        int prev = nums[0];
        
        for(int i=1;i<nums.length;i++){
            
            if(nums[i]==prev){
                tempLen++;
            }else{
                
                if(prev>maxEle){
                    maxEle = prev;
                    maxLen = tempLen;
                }else if (prev==maxEle && tempLen>=maxLen){
                    maxLen = tempLen;
                }
                tempLen=1;
                prev=nums[i];
            }
            
        }
        
        if(prev>maxEle){
            maxLen = tempLen;
        }else if (prev==maxEle && tempLen>maxLen){
            maxLen = tempLen;
        }
        
        return maxLen;
        
    }
}