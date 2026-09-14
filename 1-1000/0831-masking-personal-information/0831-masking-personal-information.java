class Solution {
    public String maskPII(String s) {
        char ch1st = s.charAt(0);
        StringBuilder sb = new StringBuilder();
        if ( ('A' <= ch1st && ch1st <= 'Z') || ('a' <= ch1st && ch1st <= 'z') ) {
            if ('A' <= ch1st && ch1st <= 'Z') ch1st += 'a' - 'A';
            sb.append(ch1st) ;
            int i = 1;
            if (s.charAt(1) != '@') {
                for(; i < s.length() && s.charAt(i+1) != '@'; i++) ;
                char ch = s.charAt(i);
                if ('A' <= ch && ch <= 'Z') ch += 'a' - 'A';
                sb.append("*****");
                sb.append(ch);
                i++;
            }
            sb.append(s.substring(i).toLowerCase());         
        } else {
            int count = 0;
            char[] digits = new char[4];
            for (int i = s.length() - 1, j= 3; i >= 0 ; i--) {
                char ch = s.charAt(i); 
                if ('0' <= ch && ch <= '9') {
                    count++;
                    if (j >=0) digits[j--] = ch;
                }
            }
            if(count > 10){
                sb.append('+');
                for(;count > 10; count--) sb.append('*');
                sb.append('-');
            }
            sb.append("***-***-");
            for(int j = 0; j < 4; j++) sb.append(digits[j]);
        }
        return sb.toString();
    }
}