class Solution {
    public String removeDuplicateLetters(String s) {
        int[] count = new int [26];
        boolean[] used = new boolean [26];

        for (char ch : s.toCharArray()){
            count[ch - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()){
            count[ch - 'a']--;
            if (used[ch - 'a']){
                continue;
            }
            while (!sb.isEmpty() && sb.charAt(sb.length()-1) > ch && count[sb.charAt(sb.length()-1) - 'a'] > 0){
                used[sb.charAt(sb.length()-1)-'a'] = false;

                sb.deleteCharAt(sb.length()-1);
            }

            sb.append(ch);
            used[ch-'a'] = true;
        }
        return sb.toString();
    }
}