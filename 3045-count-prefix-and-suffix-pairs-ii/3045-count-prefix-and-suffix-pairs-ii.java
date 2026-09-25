// can 
class Solution {
    public long countPrefixSuffixPairs(String[] words) {
        TrieNode trieNode = new TrieNode();
        long result = 0;
        for(int i=0; i<words.length; i++) {
            result += trieNode.insert(words[i]);
        }
        return result;
    }
}
class TrieNode {
    TrieNode[] children;
    boolean isLeaf;
    int wordCount;
    // int sameSuffCount;
    public TrieNode() {
        children = new TrieNode[26];
    }
    public int insert(String word) {
        TrieNode current = this;
        // boolean suffCheck = true;
        int n = word.length();
        int res = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<word.length(); i++) {
            int index = word.charAt(i)-'a';
            if(current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
            // if(suffCheck && word.charAt(i)==word.charAt(n-1-i)){
            //     current.sameSuffCount++;
            // } else {
            //     suffCheck = false;
            // }
            if(current.isLeaf ){
                String c = word.substring(0,i+1);
                // System.out.println(c);
                if(word.endsWith(c)){
                    res+= current.wordCount;
                }
            }
        }
        current.isLeaf = true;
        current.wordCount++;
        return res;
    }

}