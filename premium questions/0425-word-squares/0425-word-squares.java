class Trie{
    TrieNode root;
    List<List<String>> sol;
    int len;

    Trie(String[] words){
        this.root = new TrieNode();
        for (String w : words) add(w);
        this.sol = new ArrayList<>();
        this.len = words[0].length();
    }

    public void add(String w){
        TrieNode ptr = this.root;
        for (char ch : w.toCharArray()){
            if (ptr.children[ch - 'a'] == null){
                ptr.children[ch - 'a'] = new TrieNode();
            }
            ptr.words.add(w);
            ptr = ptr.children[ch - 'a'];
        }
        ptr.words.add(w);
    }

    public void find(List<String> curr){
        if (curr.size() >= this.len){
            this.sol.add(new ArrayList<>(curr));
            return;
        }

        TrieNode ptr = this.root;
        int row = curr.size();
        
        for (int col = 0; col < row; col++){
            char need = curr.get(col).charAt(row);
            if (ptr.children[need - 'a'] == null) return;
            ptr = ptr.children[need - 'a'];
        }

        for (String match : ptr.words){
            curr.add(match);
            find(curr);
            curr.remove(curr.size() - 1);
        }
    }
}

class TrieNode{
    TrieNode[] children;
    List<String> words;
    TrieNode(){
        this.children = new TrieNode[26];
        this.words = new ArrayList<>();
    }
}

class Solution {
    public List<List<String>> wordSquares(String[] words) {
        Trie trie = new Trie(words);
        trie.find(new ArrayList<>());
        return trie.sol;
    }
}