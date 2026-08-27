class Solution {
    public boolean differByOne(String[] dict) {
        long[] hash = new long[dict.length];
        for(int j = 0; j < dict.length; j++) {
            String word = dict[j];
            long wordhash = 0;
            for(int i = 0; i < word.length(); i++) {
                wordhash = wordhash * 26 + (word.charAt(i) - 'a');
            }
            hash[j] = wordhash;
        }
        Set<Long> stringHashes = new HashSet<>();
        int length = dict[0].length();
        long base = 1;
        for(int j = length - 1; j >= 0; j--) { 
            stringHashes.clear();
            for(int i = 0; i < dict.length; i++) {
                long curHash = hash[i] - (base * (dict[i].charAt(j) - 'a'));
                if(!stringHashes.add(curHash)) {
                    return true;
                }
                
            }
            base = base * 26;

        }
        return false;
    }
}