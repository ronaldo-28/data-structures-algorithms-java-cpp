class Solution {
  
   static final boolean[] vowel = new boolean[128];
   static {
        vowel['a'] = vowel['e'] = vowel['i'] = vowel['o'] = vowel['u'] = true;
        vowel['A'] = vowel['E'] = vowel['I'] = vowel['O'] = vowel['U'] = true;
    }

    public String reverseVowels(String s) {

        char[] data = s.toCharArray();
        int left = 0;
        int right = data.length -1;

        while(left < right) {

            if (!vowel[data[left]]) {
                left++;
                continue;
            }

            if (!vowel[data[right]]) {
                right--;
                continue;
            }

            char temp = data[left];
            data[left] = data[right];
            data[right] = temp;
            
            left++;
            right--;
        }

        return new String(data);
    }
}