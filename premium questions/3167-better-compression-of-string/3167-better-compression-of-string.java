class Solution {
    public String betterCompression(String compressed) {
        int[] counter = new int[26];
        int i = 0;
        while (i < compressed.length()) {
            char c = compressed.charAt(i);
            i++;
            int freq = 0;
            while ( i < compressed.length() && compressed.charAt(i) >= '0' && compressed.charAt(i) <='9') {
                freq = freq * 10 + compressed.charAt(i) - '0';
                i++;
            }
            counter[c-'a'] += freq;
        }
        StringBuilder builder =new StringBuilder();
        for(int j = 0 ; j < 26; j++) {
            if (counter[j] == 0)
                continue;
            char c = (char)(j+'a');
            builder.append(c);
            builder.append(counter[j]);
        }
        return builder.toString();
    }
}