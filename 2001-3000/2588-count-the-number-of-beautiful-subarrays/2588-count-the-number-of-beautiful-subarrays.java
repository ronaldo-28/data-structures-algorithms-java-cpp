class Solution {
    public long beautifulSubarrays(int[] nums) {
        int mx = 0 , xor = 0;
        for( int f : nums){
            xor ^= f;

            if( xor > mx ) mx = xor;
        }

        long count =0;

        int[] map = new int[mx + 1];
        
        xor = 0;
        for( int f : nums){
            xor ^= f;

            if( xor == 0) count++;

            count += map[xor];

            map[xor] += 1;
        }
        return count;
    }

    public long beautifulSubarraysMethodTwoPrefix(int[] nums) {
        long count = 0;
        
        HashMap<Long , Long> mp = new HashMap<>();
        long prefixXor = 0;

        mp.put((long)0 , (long)1);
        for( int i = 0 ; i < nums.length; i++){
            prefixXor ^= nums[i];
            
            if( mp.containsKey(prefixXor)){
                count += mp.get(prefixXor);
            }

            mp.put(prefixXor, mp.getOrDefault( prefixXor , (long)0) + 1);
        }
        return count;
    }
}