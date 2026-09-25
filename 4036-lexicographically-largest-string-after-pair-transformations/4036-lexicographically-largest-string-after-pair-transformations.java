class Solution {
    public String[] largestString(int[] nums) {
        //aaaaaa - 6
        //bbb
        //cb
        //110 - 6 in binary
        //cba - answer corresponds to the binary
        
        int n = nums.length;
        String[] ans = new String[n];
        for(int i = 0; i < n; i++) {
            int num = nums[i];
            
            int mask = num & 0b1111111111111111111111111;
            int zCount = num >>> 25;
            
            char[] arr = new char[Integer.bitCount(mask) + zCount];
            for(int j = 0; j < zCount; j++) arr[j] = 'z';

            int index = arr.length - 1;
            char c = 'a';
            while(mask > 0) {
                if((mask & 1) == 1) arr[index--] = c;
                c++;
                mask >>>= 1;
            }
            ans[i] = String.valueOf(arr);
        }
        return ans;
    }
}