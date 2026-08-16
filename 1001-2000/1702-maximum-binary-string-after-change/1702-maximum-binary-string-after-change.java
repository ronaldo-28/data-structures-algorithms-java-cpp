class Solution {
    public String maximumBinaryString(String binary) {
        char[] arr = binary.toCharArray();
        int N = arr.length;
        int cnt = 0;

        int prefix = 0;
        while ( prefix < N && arr[prefix] == '1' ) prefix++;
        if ( prefix == N ) return binary;

        for ( int ii = prefix; ii < N; ii++ ) {
            cnt += arr[ii] & 1;
            arr[ii] = '1';
        }

        if ( N-cnt-1 >= 0 ) arr[N-cnt-1] = '0';
        return new String(arr);
    }
}