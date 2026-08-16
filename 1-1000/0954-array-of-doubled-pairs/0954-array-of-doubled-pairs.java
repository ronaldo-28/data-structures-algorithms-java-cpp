class Solution {
    public boolean canReorderDoubled(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for ( int num : arr ) {
            if ( min > num ) min = num;
            if ( max < num ) max = num;
        }
        int limit = max - min + 1;
        int[] count = new int[limit];
        for ( int num : arr ) count[num - min]++;
        for ( int num = min; num <= max; num++ ) {
            int cnt = count[num-min];
            if ( cnt == 0 ) continue;

            int num2 = 0;
            if ( num < 0 ) {
                if ( (num & 1) == 1 ) return false;
                num2 = num >> 1;
            }
            else 
                num2 = num << 1;
            if ( num2 < min || num2 > max || count[num2-min] < cnt ) return false;
            count[num2-min] -= cnt;
        }

        return true;
    }
}