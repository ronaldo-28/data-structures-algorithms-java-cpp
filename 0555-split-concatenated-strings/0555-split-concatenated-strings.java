class Solution {
    public String splitLoopedString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            String rev = new StringBuilder(str).reverse().toString();
            if (str.compareTo(rev) < 0) {
                sb.append(rev);
            }
            else {
                sb.append(str);
            }
        }
        String s = sb.toString();
        
        int n = strs.length;
        String result = "a";
        int current = 0;
        
        for (int i = 0; i < n; i++) {
            String s1 = strs[i];
            String s2 = new StringBuilder(s1).reverse().toString();
            String mid = s.substring(current + s1.length()) + s.substring(0, current);
            
            for (int j = 0; j < s1.length(); j++) {
                if (s1.charAt(j) >= result.charAt(0)) {
                    String t = s1.substring(j) + mid + s1.substring(0, j);
                    result = (result.compareTo(t) >= 0) ? result : t;
                }
                if (s2.charAt(j) >= result.charAt(0)) {
                    String t = s2.substring(j) + mid + s2.substring(0, j);
                    result = (result.compareTo(t) >= 0) ? result : t;
                }
            }
            current += s1.length();
        }
        return result;
    }
}