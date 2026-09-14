public class Solution {
     public String smallestString(String s) {
        int n = s.length();
        StringBuilder str = new StringBuilder(s);
        int ind = 0;

        while (ind < n && s.charAt(ind) == 'a') ind++;

        if (ind == n)
        {
            str.setCharAt(n - 1, 'z');
        }
        else
        {
            while (ind < n && s.charAt(ind) != 'a')
            {
                str.setCharAt(ind, (char)(s.charAt(ind++) - 1));
            }
        }

        return str.toString();
    }
   
}