class Solution {
    public int vowelStrings(String[] words, int left, int right) {
        int count = 0;
        while(left<=right){
            String s = words[left];
            char start = s.charAt(0);
            char end = s.charAt(s.length()-1);
            if((start == 'a'||start =='e'||start=='i'||start == 'o'|| start == 'u')&& (end=='a'|| end=='e'|| end == 'i'||end == 'o'||end == 'u')){
                count++;
            }
            left++;
        }
        return count;
    }
}

        
    