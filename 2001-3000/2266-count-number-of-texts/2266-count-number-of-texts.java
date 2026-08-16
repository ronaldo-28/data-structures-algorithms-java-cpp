class Solution {
    static long MOD = 1000_000_007;

    public int countTexts(String pressedKeys) {
        char[] chars = pressedKeys.toCharArray();
        long result = 1;
        for(int i = 0; i < chars.length; i++){
            char begin = chars[i];
            int num = 1;
            while(i < chars.length - 1 && begin == chars[i + 1]) {
                i++;
                num++;
            }
            long check = check(begin , num);
            result = (result * check ) % MOD;
        }
        return (int) result;
  }

    private long check(int begin, int num) {
        if (begin == '7' || begin == '9') {
            return check4(num);
        } else {
            return check3(num);
        }

    }

    private long check3(int num) {
        long[] results = new long[num];
        results[0] = 1;
        if (num == 1) {
            return 1;
        }
        results[1] = 2;
        if (num == 2) {
            return 2;
        }
        results[2] = 4;
        if (num == 3) {
            return 4;
        }
        for (int i = 3; i < num; i++) {
            results[i] = (results[i - 3] + results[i - 2] + results[i - 1]) % MOD;
        }
        return results[num - 1];
    }

    private long check4(int num) {
        long[] results = new long[num];
        results[0] = 1;
        if (num == 1) {
            return 1;
        }
        results[1] = 2;
        if (num == 2) {
            return 2;
        }
        results[2] = 4;
        if (num == 3) {
            return 4;
        }
        results[3] = 8;
        if (num == 4) {
            return 8;
        }
        for (int i = 4; i < num; i++) {
            results[i] = (results[i - 4] + results[i - 3] + results[i - 2] + results[i - 1]) % MOD;
        }
        return results[num - 1];
    }

}