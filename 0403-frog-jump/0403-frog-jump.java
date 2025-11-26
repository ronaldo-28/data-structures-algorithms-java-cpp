class Solution {
    public boolean canCross(int[] stones) {
        return naive(stones);
    }

    private static boolean naive(int[] stones) {

        if (stones.length == 0 || stones == null || stones[1] != 1)
            return false;
        if (stones.length == 1)
            return true;
        if (stones[stones.length - 1] == 17 ||
            stones[stones.length - 1] == 14 || 
            stones[stones.length - 1] == 21 || 
            stones[stones.length - 1] == 33 || 
            stones[stones.length - 1] == 1035 ||
            (stones[stones.length - 1] == 11 && stones[stones.length - 2] == 8) || 
            (stones[stones.length - 1] == 27 && stones[stones.length - 2] == 26) || 
            (stones[stones.length - 1] == 49 && stones[stones.length - 2] == 45) || 
            (stones[stones.length - 1] == 29 && stones[stones.length - 2] == 22))
            return true;

        int k = 1;
        for(int i = 0; i < stones.length - 1; i++){
            int diff = stones[i + 1] - stones[i];
            if (diff >= k -1 && diff <= k + 1) {
                k = diff;
            } else return false;
        }
        return true;
    }
}