class Solution {
    public int bestClosingTime(String customers) {
        int[] ch2num = new int['Z'+1];
        ch2num['N'] = 1;
        ch2num['Y'] = -1;
        char[] arr = customers.toCharArray();
        int N = arr.length;

        int cnt = 0;
        int result = 0;
        int min = N;
        for ( int ii = 0; ii < N; ii++ ) {
            if ( min > cnt ) {
                result = ii;
                min = cnt;
            }
            // if ( arr[ii] == 'Y' ) cnt--; else cnt++;
            cnt += ch2num[arr[ii]];
        }

        if ( min > cnt ) {
            result = N;
            min = cnt;
        }
        return result;
    }
}