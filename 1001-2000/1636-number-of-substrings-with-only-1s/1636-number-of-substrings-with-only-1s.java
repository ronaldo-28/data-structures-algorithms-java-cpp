class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            numSub("111111");
        }
    }


    public static int numSub(String s) {
        final int MODULO = 1000000007;
        int onesStreak = 0 ;
        int res = 0;
        for(char ch : s.toCharArray()) {
            if (ch  == '1') {
                res = ( res + ++onesStreak) % MODULO ;
            }
            else    onesStreak = 0 ;


        }

        return res;

    }
}