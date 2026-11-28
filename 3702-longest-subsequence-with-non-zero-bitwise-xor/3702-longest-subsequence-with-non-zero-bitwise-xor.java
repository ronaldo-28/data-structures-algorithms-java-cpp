class Solution {
    
    public int longestSubsequence(int[] nums) {

        /* XOR properties --> x ^ x=0 , x ^ Y=z , x^0=x; **/

        /** if any number contribute to make xor 0, just remove that number and xor of whole array now non-zero, so return n-1 */
        
        /** in case all the element of array is zero ---> xor never be non-zero so return zero in that case  */
        
       int xor=0;
       int countZero=0;

       for(int num : nums){
            xor ^=num;
            if(num==0){
                countZero++;
            }
       }

       if(xor != 0) return nums.length;
       else{
        if(countZero==nums.length) return 0;
       }
       return nums.length-1;
    }
}