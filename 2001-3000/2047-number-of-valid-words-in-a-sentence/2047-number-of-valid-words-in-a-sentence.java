class Solution {
    public int countValidWords(String sentence) {

        int left = 0;
        int right = 0;

        int digitCnt = 0;
        int cnt = 0;
        while(right < sentence.length()) {

            char c = sentence.charAt(right);
            
            if(c != ' ') {
                if(c >= '0' && c <= '9') {
                    digitCnt++;
                }
                
                right++;
                continue;
            }

            if(digitCnt == 0 && isValid(sentence, left, right)) {
                cnt++;
            }

            left = right + 1;
            right = left;

            digitCnt = 0;
        }

        if(digitCnt == 0 && isValid(sentence, left, right)) {
            cnt++;
        }

        return cnt;
    }

    private boolean isValid(String str, int left, int right) {

        if(left == right) {
            return false;
        }

        int punctCnt = 0;
        int punctIdx = -1;

        int hyphenCnt = 0;
        int hyphenIdx = -1;

        for(int i = left; i < right; i++) {

            char c = str.charAt(i);
            if(c == '-') {
                hyphenCnt++;
                hyphenIdx = i;
            } else if(c == '!' || c == '.' || c == ',') {
                punctCnt++;
                punctIdx = i;
            }
        }

        if(punctCnt > 1 || hyphenCnt > 1) {
            return false;
        }

        if(punctCnt == 1 && punctIdx != right - 1) {
            return false;
        }

        if(hyphenCnt == 1) {

            char prev = hyphenIdx > 0 ? str.charAt(hyphenIdx - 1) : 0;
            char next = hyphenIdx < str.length() - 1 ? str.charAt(hyphenIdx + 1) : 0;

            return prev >= 'a' && prev <= 'z' && next >= 'a' && next <= 'z';
        }

        return true; 
    }
}