class Solution {
    public int[] minOperations(String boxes) {
        int lBall = 0;
        int lMove = 0;
        int rBall = 0;
        int rMove = 0;

        char[] arr = boxes.toCharArray();
        int N = arr.length;
        for ( int ii = 0; ii < N; ii++ ) {
            if ( arr[ii] == '1' ) {
                rBall++;
                rMove += ii + 1; 
            }
        }

        int[] result = new int[N];
        for ( int ii = 0; ii < N; ii++ ) {
            int ball = arr[ii] & 1;

            rMove -= rBall;
            rBall -= ball;

            result[ii] = rMove + lMove;
// System.out.printf("ii=%d, lBall=%d, lMove=%d, rBall=%d, rMove=%d\n", ii, lBall, lMove, rBall, rMove);

            lBall += ball;
            lMove += lBall;
        }
        return result;
    }
}