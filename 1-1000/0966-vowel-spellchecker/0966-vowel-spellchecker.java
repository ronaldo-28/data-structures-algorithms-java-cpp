class Solution {
    class TrieNode {
        TrieNode[] children;
        String val;
        boolean end;
        public TrieNode() {
            children = new TrieNode[27]; // 0..25: a-z, 26: vowel wildcard
            val = "";
            end = false;
        }
    }

    TrieNode root = new TrieNode();

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public void insert(String str) {
        TrieNode node = root;
        TrieNode vowel = root;
        for (int i = 0; i < str.length(); i++) {
            char curr = Character.toLowerCase(str.charAt(i));

            // vowel trie path
            int vIdx = isVowel(curr) ? 26 : curr - 'a';
            if (vowel.children[vIdx] == null) vowel.children[vIdx] = new TrieNode();
            vowel = vowel.children[vIdx];

            // normal trie path
            int idx = curr - 'a';
            if (node.children[idx] == null) node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        // keep the first word only
        if (!node.end) {
            node.end = true;
            node.val = str;
        }
        if (!vowel.end) {
            vowel.end = true;
            vowel.val = str;
        }
    }

    public String exist(String str) {
        TrieNode node = root;      // normal path
        TrieNode vowelNode = root; // vowel-insensitive path
        boolean regularFailed = false;
        boolean vowelFailed = false;

        for (int i = 0; i < str.length(); i++) {
            char c = Character.toLowerCase(str.charAt(i));

            // normal path
            if (!regularFailed) {
                int idx = c - 'a';
                if (idx < 0 || idx >= 26 || node.children[idx] == null) {
                    regularFailed = true;
                } else {
                    node = node.children[idx];
                }
            }

            // vowel path
            if (!vowelFailed) {
                int vIdx = isVowel(c) ? 26 : c - 'a';
                if (vIdx < 0 || vIdx >= 27 || vowelNode.children[vIdx] == null) {
                    vowelFailed = true;
                } else {
                    vowelNode = vowelNode.children[vIdx];
                }
            }
        }

        if (!regularFailed && node.end && node.val != null && !node.val.isEmpty()) {
            return node.val;
        }
        if (!vowelFailed && vowelNode.end && vowelNode.val != null && !vowelNode.val.isEmpty()) {
            return vowelNode.val;
        }
        return "";
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exact = new HashSet<>();
        for (String w : wordlist) {
            exact.add(w);
            insert(w);
        }

        String[] res = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            if (exact.contains(q)) {
                res[i] = q;
            } else {
                res[i] = exist(q);
            }
        }
        return res;
    }
}