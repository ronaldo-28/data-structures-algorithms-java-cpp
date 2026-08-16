class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        int plus1 = 0 , plus2 = 0;
        int len = num1.length();
        if(num2.length() > len) len = num2.length();
        for(int i = 0 ; i < len ; i++){
            if(num1.charAt(i) == '+') plus1 = i;
            if(num2.charAt(i) == '+') plus2 = i;
            if(plus1 != 0 && plus2 != 0) break;
        }
        String r1 = num1.substring(0 , plus1);
        String r2 = num2.substring(0 , plus2);
        String ig1 = num1.substring( plus1 +1 , num1.length() -1);
        String ig2 = num2.substring( plus2 +1 ,  num2.length() -1);

        int real1 = 0 , real2 = 0 , imaginary1 = 0 , imaginary2 = 0 ;

        if(r1.charAt(0) == '-') real1 = -Integer.parseInt(r1.substring(1));
        else real1 =  Integer.parseInt(r1);
        if(r2.charAt(0) == '-') real2 = -Integer.parseInt(r2.substring(1));
        else real2 =  Integer.parseInt(r2);
        if(ig1.charAt(0) == '-') imaginary1 = -Integer.parseInt(ig1.substring(1));
        else imaginary1 =  Integer.parseInt(ig1);
        if(ig2.charAt(0) == '-') imaginary2 = -Integer.parseInt(ig2.substring(1));
        else imaginary2 =  Integer.parseInt(ig2);

        int realAns = (real1 * real2) - ( imaginary1 * imaginary2);
        int imaginaryAns = (real1 * imaginary2) + ( imaginary1 * real2);
        StringBuilder sb = new StringBuilder();
        sb.append(realAns);
        sb.append('+');
        sb.append(imaginaryAns);
        sb.append('i');

        return sb.toString();
    }
}