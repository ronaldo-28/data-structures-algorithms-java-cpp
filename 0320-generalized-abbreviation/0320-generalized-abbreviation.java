import java.util.AbstractList;
class Solution {
    public List<String> generateAbbreviations(String word) {
        return new AbstractList<String>() {
            public int size() {
                return 1 << word.length();
            }

            public String get(int i) {
                return combine(i, word);
            }
        };
    }

    String combine(int num, String word) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if ((num & 1) == 1) {
                if (count > 0) {
                    sb.append(count);
                    count = 0;
                } 
                sb.append(word.charAt(i));
            } else {
                count ++;
            }
            num >>= 1;
        }
        if (count > 0) {
            sb.append(count);
        }
        return sb.toString();
    }
}