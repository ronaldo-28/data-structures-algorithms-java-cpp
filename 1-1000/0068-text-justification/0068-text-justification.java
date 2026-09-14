class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int start = 0;
        while(start < words.length){
            int lineLen = words[start].length();
            int end = start + 1;
            while(end < words.length && lineLen + words[end].length() + end - start <= maxWidth){
                lineLen += words[end].length();
                end++;
            }
            StringBuilder line = new StringBuilder();
            line.append(words[start++]);
            int space = 1, remain = 0;  // intialize space for the last line.
            if(start < end && end != words.length){
                space = (maxWidth - lineLen) / (end - start);
                remain = (maxWidth - lineLen) % (end - start);
            }
            while(start < end){
                for(int i = 0; i < space; i++) line.append(" ");
                if(remain-- > 0){
                    line.append(" ");
                }
                line.append(words[start++]);
            }
            while(line.length() < maxWidth) line.append(" ");
            result.add(line.toString());
        }
        return result;
    }
}