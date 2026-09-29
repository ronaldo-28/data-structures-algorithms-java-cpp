class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int minLen = Integer.MAX_VALUE;
        int currOr = 0;
        int l = 0;
        int[] bits = new int[32];

        for(int r=0;r<nums.length;r++){
            int num = nums[r];
            currOr |= num;

            int b1 = 0;
            while(num > 0){
                if((num & 1) == 1) bits[b1]++;
                b1++;
                num = num >> 1;
            }

            while(l<=r && currOr >= k){
                minLen = Math.min(minLen, r-l+1);

                int numLeft = nums[l++];
                int b2 = 0;
                while(numLeft > 0){
                    if((numLeft & 1) == 1){
                        bits[b2]--;
                        if(bits[b2] == 0) currOr = currOr & ~(1<<b2);
                    } 
                    b2++;
                    numLeft = numLeft >> 1;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}
/*
class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int l = 0;
        int currOr = 0;
        int minLen = Integer.MAX_VALUE;
        int[] bitCount = new int[32];
        for(int r=0;r<n;r++){
            int currChar = nums[r];
            currOr |= currChar;
            int b = 0;
            while(currChar!=0){
                if((currChar & 1) == 1){
                    bitCount[b]++;
                }
                b++;
                currChar = currChar >> 1;
            }

            while(l<=r && currOr >= k){
                minLen = Math.min(minLen, r-l+1);

                int val = nums[l];
                for(int bit = 0; bit<32 && val>0; bit++){
                    if((val & 1) == 1){
                        bitCount[bit]--;
                    }
                    if(bitCount[bit]==0){
                        currOr &= ~(1<<bit);
                    }
                    val >>>= 1; // Unsigned shift right
                }
                l++;
            }
        }

        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}
// OR increase the value of a no. , but never decreases it
// 2 or 1 -> 3
// 2 or 2 -> 2

// But we need the shortest subarray
// 
*/