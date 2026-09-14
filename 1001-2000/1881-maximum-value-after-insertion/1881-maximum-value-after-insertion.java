class Solution {
    public String maxValue(String n, int x) {
        StringBuilder sb = new StringBuilder();
        int len = n.length();
        char digit = (char) (x + '0');
        
        if (n.charAt(0) != '-') {
            for (int i = 0; i < len; i++) {
                if (digit > n.charAt(i)) {
                    sb.append(n.substring(0, i));
                    sb.append(digit);
                    sb.append(n.substring(i));
                    return sb.toString();
                }
            }
        } else {
            for (int i = 1; i < len; i++) {
                if (digit < n.charAt(i)) {
                    sb.append(n.substring(0, i));
                    sb.append(digit);
                    sb.append(n.substring(i));
                    return sb.toString();
                }
            }
        }
        
        sb.append(n);
        sb.append(digit);
        return sb.toString();
    }
}