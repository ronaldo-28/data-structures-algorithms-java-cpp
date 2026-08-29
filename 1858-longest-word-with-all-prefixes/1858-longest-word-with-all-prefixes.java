class Solution {
    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        TrieNode.insert(root, "");
        for(String word : words) TrieNode.insert(root, word);
        return TrieNode.search(root);
    }
}
class TrieNode {
    private final TrieNode[] next = new TrieNode[26];
    private String word;
    public static String search(TrieNode current) {
        String ans = current.word;
        if(ans == null) return "";
        int max = ans.length();
        for(int i = 0; i < 26; i++) {
            TrieNode x = current.next[i];
            if(x != null) {
                String s = search(x);
                if(s.length() > max) {
                    ans = s;
                    max = s.length();
                }
            }
        }
        return ans;
    }
    public static void insert(TrieNode current, final String word) {
        int n = word.length();
        for(int i = 0; i < n; i++) current = current.getNext(word.charAt(i) - 'a');
        current.word = word;
    }
    private TrieNode getNext(final int id) {
        TrieNode node = next[id];
        return node == null ? next[id] = new TrieNode() : node;
    }
}