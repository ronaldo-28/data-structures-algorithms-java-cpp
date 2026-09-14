class Solution {
    public int getMaximumConsecutive(int[] coins) {
        int max = 0;
        for ( int coin : coins ) max = Math.max(max, coin);

        int[] freq = new int[max+1];
        for ( int coin : coins ) freq[coin]++;

        int result = 1;
        for ( int coin = 1; coin <= max; coin++ ) {
            int ff = freq[coin];
            if ( ff > 0 ) {
                if ( coin > result ) break;
                while (ff-- > 0) result += coin;
            }
        }
        return result;
    }
}